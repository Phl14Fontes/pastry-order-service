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
    String correlationId;

    public FriedPastry(Flavor flavor, Integer quantity) {
        this.flavor = flavor;
        this.quantity = quantity;
    }
}
