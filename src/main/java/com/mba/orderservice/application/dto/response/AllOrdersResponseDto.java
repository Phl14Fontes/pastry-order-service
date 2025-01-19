package com.mba.orderservice.application.dto.response;

import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.model.FriedPastry;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class AllOrdersResponseDto {
    String correlationId;
    String name;
    OrderStatus status;
    List<FriedPastry> friedPastries;
    Float totalAmount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public AllOrdersResponseDto(String correlationId, String name, OrderStatus status, List<FriedPastry> friedPastries, Float totalAmount, LocalDateTime createdAt) {
        this.correlationId = correlationId;
        this.name = name;
        this.status = status;
        this.friedPastries = friedPastries;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }
}
