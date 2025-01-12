package com.mba.orderservice.domain.repository;

import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.model.Order;

import java.util.List;

public interface OrderRepository {

    Order create(Order order);
    List<Order> getAll();
    Order getBy(String correlationId);
    void updateStatusByCorrelationId(OrderStatus newStatus, String correlationId);
}
