package com.mba.orderservice.application.dto;

import com.mba.orderservice.domain.enums.Flavor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FriedPastryDto {
    Flavor flavor;
    Integer quantity;
    Double amount;
    String observations;

    public FriedPastryDto(Flavor flavor, Integer quantity, Double amount) {
        this.flavor = flavor;
        this.quantity = quantity;
        this.amount = amount;
    }
}
