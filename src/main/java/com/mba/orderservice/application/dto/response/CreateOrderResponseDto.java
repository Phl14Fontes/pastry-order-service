package com.mba.orderservice.application.dto.response;

import com.mba.orderservice.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateOrderResponseDto {
    String correlationId;
    String name;
    OrderStatus status;
}
