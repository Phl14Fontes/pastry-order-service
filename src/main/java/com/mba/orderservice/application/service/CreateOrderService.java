package com.mba.orderservice.application.service;

import com.mba.orderservice.application.port.in.CreateOrderUseCase;
import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.domain.repository.OrderRepository;
import com.mba.orderservice.infrastructure.adapter.out.exception.NullFieldException;
import de.huxhorn.sulky.ulid.ULID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository repository;
    private final FriedPastryService friedPastryService;

    @Override
    public Order createOrder(Order order) {
        if (order == null) throw new NullFieldException("Order cannot be null");

        String correlationId = generateUlid();
        order.setCorrelationId(correlationId);

        repository.create(order);
        friedPastryService.createOrderItems(order.getFriedPastries(), correlationId);

        return order;
    }

    private String generateUlid() {
        return new ULID().nextULID();
    }
}
