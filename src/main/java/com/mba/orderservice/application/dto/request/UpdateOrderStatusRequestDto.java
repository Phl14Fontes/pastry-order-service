package com.mba.orderservice.application.dto.request;

import com.mba.orderservice.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateOrderStatusRequestDto {
    @NotNull
    OrderStatus status;
    @NotBlank
    String correlationId;
}
