package com.mba.orderservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoneOrderEventDto {
    String correlationId;
    String name;
}
