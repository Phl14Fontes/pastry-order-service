package com.mba.orderservice.application.dto.response;

import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.enums.PaymentMethod;
import com.mba.orderservice.domain.model.FriedPastry;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    String name;
    PaymentMethod paymentMethod;
    OrderStatus status;
    List<FriedPastry> friedPastries;
    Float totalAmount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public OrderResponseDto(String name, PaymentMethod paymentMethod, OrderStatus status, List<FriedPastry> friedPastries, Float totalAmount, LocalDateTime createdAt) {
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.friedPastries = friedPastries;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }
}
