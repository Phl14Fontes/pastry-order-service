package com.mba.orderservice.domain.model;

import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.enums.PaymentMethod;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Order {
    String correlationId;
    String name;
    PaymentMethod paymentMethod;
    OrderStatus status;
    List<FriedPastry> friedPastries;
    Float totalAmount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Order(String name, PaymentMethod paymentMethod, OrderStatus status, List<FriedPastry> friedPastries, Float totalAmount) {
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.friedPastries = friedPastries;
        this.totalAmount = totalAmount;
    }



    public Order(String correlationId, String name, PaymentMethod paymentMethod, OrderStatus status, List<FriedPastry> friedPastries, Float totalAmount, LocalDateTime createdAt) {
        this.correlationId = correlationId;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.friedPastries = friedPastries;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }
}
