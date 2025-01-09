package com.mba.orderservice.application.dto.request;

import com.mba.orderservice.application.dto.FriedPastryDto;
import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class CreateOrderRequestDto {
    String name;
    PaymentMethod paymentMethod;
    OrderStatus status;
    List<FriedPastryDto> order;
    Double totalAmount;
}
