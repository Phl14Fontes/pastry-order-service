package com.mba.orderservice.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Flavor {
    CHEESE(7.0F),
    MEAT(9.0F),
    CHICKEN(8.0F),
    PIZZA(9.0F),
    PALM_HEART(10.0F),
    BAURU(7.5F),
    SHRIMP(10.0F);

    private final Float unitPrice;
}
