package com.mba.orderservice.domain.model;

import com.mba.orderservice.domain.enums.Flavor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FriedPastry {
    Flavor flavor;
    Integer quantity;
    Float amount;
    String observations;

    public FriedPastry(Flavor flavor, Integer quantity, Float amount) {
        this.flavor = flavor;
        this.quantity = quantity;
        this.amount = amount;
    }
}
