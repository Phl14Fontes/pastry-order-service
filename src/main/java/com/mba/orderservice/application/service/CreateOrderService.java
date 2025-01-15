package com.mba.orderservice.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mba.orderservice.application.dto.DoneOrderEventDto;
import com.mba.orderservice.application.exception.PrepareOrderException;
import com.mba.orderservice.application.port.in.CreateOrderUseCase;
import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.domain.repository.OrderRepository;
import com.mba.orderservice.infrastructure.adapter.out.async.KafkaProducerAdapter;
import com.mba.orderservice.infrastructure.adapter.out.exception.NullFieldException;
import de.huxhorn.sulky.ulid.ULID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository repository;
    private final FriedPastryService friedPastryService;
    private final KafkaProducerAdapter adapter;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public Order createOrder(Order order) {
        if (order == null) throw new NullFieldException("Order cannot be null");

        String correlationId = generateUlid();
        order.setCorrelationId(correlationId);

        repository.create(order);
        friedPastryService.createOrderItems(order.getFriedPastries(), correlationId);

        scheduler.schedule(() -> {
            try {
                prepareOrder(correlationId, order.getName());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }, 5, TimeUnit.SECONDS);

        return order;
    }

    @Transactional
    private void prepareOrder(String correlationId, String name) throws JsonProcessingException {
        try {
            repository.updateStatusByCorrelationId(OrderStatus.DONE, correlationId);
        } catch (Throwable ex) {
            throw new PrepareOrderException(ex.getMessage());
        }

        adapter.sendMessage(new DoneOrderEventDto(correlationId, name));
    }

    private String generateUlid() {
        return new ULID().nextULID();
    }
}
