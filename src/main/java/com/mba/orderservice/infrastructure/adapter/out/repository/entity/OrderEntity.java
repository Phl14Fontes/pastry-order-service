package com.mba.orderservice.infrastructure.adapter.out.repository.entity;

import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"id"})
@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Size(min = 26, max = 26)
    private String correlationId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    private Float totalAmount;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderEntity(String name, String correlationId, PaymentMethod paymentMethod, OrderStatus status, Float totalAmount) {
        this.name = name;
        this.correlationId = correlationId;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.totalAmount = totalAmount;
    }
}
