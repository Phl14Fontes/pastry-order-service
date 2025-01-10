package com.mba.orderservice.domain.repository;

import com.mba.orderservice.domain.model.Order;

public interface OrderRepository {

    Order create(Order order);
}
