package com.mba.orderservice.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mba.orderservice.application.dto.DoneOrderEventDto;
import com.mba.orderservice.application.exception.OrderNotFoundException;
import com.mba.orderservice.application.exception.UnprocessableEntityException;
import com.mba.orderservice.application.port.in.UpdateOrderStatusUseCase;
import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.domain.repository.OrderRepository;
import com.mba.orderservice.infrastructure.adapter.out.async.KafkaProducerAdapter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderService implements UpdateOrderStatusUseCase {

    private final OrderRepository repository;
    private final KafkaProducerAdapter adapter;

    @Override
    public void updateStatus(OrderStatus status, String correlationId) {
        Order order = repository.getBy(correlationId);

        validateOrderToUpdate(status, order, correlationId);

        repository.updateStatusByCorrelationId(status, order.getCorrelationId());

        logger.info("Order with correlation id [{}] updated to status [{}] successfully", correlationId, status);

        sendEvent(correlationId, order.getName());
    }

    @Override
    public void cancelOrder(String correlationId) {
        Order order = repository.getBy(correlationId);

        validateOrderToCancel(order, correlationId);

        repository.updateStatusByCorrelationId(OrderStatus.CANCELED, correlationId);
    }

    private void sendEvent(String correlationId, String name) {
        try {
            adapter.sendMessage(new DoneOrderEventDto(correlationId, name));
        } catch (JsonProcessingException exception) {
            logger.error(exception.getMessage());
        }
    }

    private void validateOrderToUpdate(OrderStatus status, Order order, String correlationId) {
        if (order == null) {
            orderNotFoundByCorrelationIdErrorMessage(correlationId);
            throw new OrderNotFoundException("Order not found by correlation id");
        }

        if (status == order.getStatus() || status != OrderStatus.DONE)
            throw new UnprocessableEntityException("Order status cannot be update");
    }

    private void validateOrderToCancel(Order order, String correlationId) {
        if (order == null) {
            orderNotFoundByCorrelationIdErrorMessage(correlationId);
            throw new OrderNotFoundException("Order not found by correlation id");
        }
    }

    private void orderNotFoundByCorrelationIdErrorMessage(String correlationId) {
        logger.error("Order not found by correlation id [{}]", correlationId);
    }

    private static final Logger logger = LogManager.getLogger(UpdateOrderService.class.getName());
}
