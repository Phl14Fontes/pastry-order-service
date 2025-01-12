package com.mba.orderservice.application.port.in;

import com.mba.orderservice.domain.enums.OrderStatus;

public interface UpdateOrderStatusUseCase {
    void updateStatus(OrderStatus status, String correlationId);
    void cancelOrder(String correlationId);
}
