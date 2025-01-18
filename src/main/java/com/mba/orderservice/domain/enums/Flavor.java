package com.mba.orderservice.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Flavor {
    CHEESE(7.0F, "Pastel com delicioso recheio de Queijo"),
    MEAT(9.0F, "Pastel com delicioso recheio de Carne"),
    CHICKEN(8.0F, "Pastel com delicioso recheio de Frango"),
    PIZZA(9.0F, "Pastel com delicioso recheio de Pizza"),
    PALM_HEART(10.0F, "Pastel com delicioso recheio de Palmito"),
    BAURU(7.5F, "Pastel com delicioso recheio de Bauru"),
    SHRIMP(10.0F, "Pastel com delicioso recheio de Camarão");

    private final Float unitPrice;
    private final String description;
}
