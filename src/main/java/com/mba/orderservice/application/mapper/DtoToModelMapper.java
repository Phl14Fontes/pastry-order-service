package com.mba.orderservice.application.mapper;

import com.mba.orderservice.application.dto.FriedPastryDto;
import com.mba.orderservice.application.dto.request.CreateOrderRequestDto;
import com.mba.orderservice.application.exception.BadRequestException;
import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.model.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DtoToModelMapper {

    public static Order toCreateOrderModel(CreateOrderRequestDto dto) {
        validate(dto);

        List<FriedPastry> friedPastries = toFriedPastryModelList(dto.getFriedPastries());
        Float totalAmount = calculateTotalAmount(friedPastries);

        return new Order(dto.getName(), dto.getPaymentMethod(), OrderStatus.IN_PROGRESS, friedPastries, totalAmount);
    }

    private static List<FriedPastry> toFriedPastryModelList(List<FriedPastryDto> dtoList) {
        List<FriedPastry> friedPastries = new ArrayList<>();

        for (FriedPastryDto dto : dtoList) {
            FriedPastry friedPastry = new FriedPastry(dto.getFlavor(), dto.getQuantity(), dto.getCorrelationId(), dto.getAmount(), dto.getObservations());
            friedPastries.add(friedPastry);
        }

        return friedPastries;
    }

    private static void validate(CreateOrderRequestDto dto) {
        if (dto == null) throw new BadRequestException("Create order request dto cannot be null");
        if (dto.getFriedPastries().isEmpty()) throw new BadRequestException("Order cannot be empty");
    }

    private static Float calculateTotalAmount(List<FriedPastry> friedPastries) {
        return friedPastries.stream()
                .map(friedPastry -> friedPastry.getAmount() * friedPastry.getQuantity())
                .reduce(0.0F, Float::sum);
    }
}
