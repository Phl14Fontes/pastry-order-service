package com.mba.orderservice.domain.model;

import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.enums.PaymentMethod;
import lombok.*;

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
    List<FriedPastry> order;
    Double totalAmount;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;

    public Order(String name, PaymentMethod paymentMethod, OrderStatus status, List<FriedPastry> order, Double totalAmount) {
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.order = order;
        this.totalAmount = totalAmount;
    }
}
