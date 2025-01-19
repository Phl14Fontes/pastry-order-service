package com.mba.orderservice.application.port.in;

import com.mba.orderservice.application.dto.response.CreateOrderResponseDto;
import com.mba.orderservice.domain.model.Order;

public interface CreateOrderUseCase {
    Order createOrder(Order order);
}
