package com.mba.orderservice.application.port.in;

import com.mba.orderservice.domain.model.Order;

public interface CreateOrderUseCase {
    String createOrder(Order order);
}
