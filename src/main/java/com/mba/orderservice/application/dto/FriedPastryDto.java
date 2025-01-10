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
    Float amount;
    String observations;

    public FriedPastryDto(Flavor flavor, Integer quantity, Float amount) {
        this.flavor = flavor;
        this.quantity = quantity;
        this.amount = amount;
    }
}
