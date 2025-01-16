package com.mba.orderservice.infrastructure.adapter.out.repository.entity;

import com.mba.orderservice.domain.enums.Flavor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"id"})
@Entity(name = "order_items")
public class FriedPastryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 26, max = 26)
    private String correlationId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    Flavor flavor;

    @NotNull
    @Positive
    Integer quantity;

    public FriedPastryEntity(String correlationId, Flavor flavor, Integer quantity) {
        this.correlationId = correlationId;
        this.flavor = flavor;
        this.quantity = quantity;
    }
}
