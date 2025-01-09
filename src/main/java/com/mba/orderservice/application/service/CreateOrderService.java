package com.mba.orderservice.application.service;

import com.mba.orderservice.application.port.in.CreateOrderUseCase;
import com.mba.orderservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    @Override
    public String createOrder(Order dto) {
        // TODO 01 - Validate order
        // TODO 02 - Persist order and return correlation id from order
        return null;
    }
}
