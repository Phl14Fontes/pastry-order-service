package com.mba.orderservice.application.port.in;

import com.mba.orderservice.domain.model.Order;

import java.util.List;

public interface GetOrdersUseCase {
    List<Order> getAll();

    Order getBy(String correlationId);
}
