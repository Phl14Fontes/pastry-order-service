package com.mba.orderservice.application.dto;

import com.mba.orderservice.domain.enums.Flavor;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FriedPastryDto {
    Flavor flavor;
    Integer quantity;
    String correlationId;

    public FriedPastryDto(Flavor flavor, Integer quantity) {
        this.flavor = flavor;
        this.quantity = quantity;
    }
}
