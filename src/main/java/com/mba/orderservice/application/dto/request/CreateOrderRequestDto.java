package com.mba.orderservice.application.dto.request;

import com.mba.orderservice.application.dto.FriedPastryDto;
import com.mba.orderservice.domain.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateOrderRequestDto {
    @NotBlank
    String name;
    @NotNull
    PaymentMethod paymentMethod;
    @NotNull
    List<FriedPastryDto> friedPastries;
}
