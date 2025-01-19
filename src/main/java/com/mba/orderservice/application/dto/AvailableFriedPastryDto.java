package com.mba.orderservice.application.dto;

import com.mba.orderservice.domain.enums.Flavor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AvailableFriedPastryDto {
    Flavor flavor;
    Float unitPrice;
    String description;
}
