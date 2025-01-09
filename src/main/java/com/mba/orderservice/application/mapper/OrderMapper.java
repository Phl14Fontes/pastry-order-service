package com.mba.orderservice.application.mapper;

import com.mba.orderservice.application.dto.FriedPastryDto;
import com.mba.orderservice.application.dto.request.CreateOrderRequestDto;
import com.mba.orderservice.application.exception.BadRequestException;
import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order toModel(CreateOrderRequestDto dto) {
        validate(dto);

        List<FriedPastry> friedPastries = toFriedPastryModelList(dto.getOrder());

        return new Order(dto.getName(), dto.getPaymentMethod(), dto.getStatus(), friedPastries, dto.getTotalAmount());
    }

    private static List<FriedPastry> toFriedPastryModelList(List<FriedPastryDto> dtoList) {
        List<FriedPastry> friedPastries = new ArrayList<>();

        for (FriedPastryDto dto : dtoList) {
            FriedPastry friedPastry = new FriedPastry(dto.getFlavor(), dto.getQuantity(), dto.getAmount(), dto.getObservations());
            friedPastries.add(friedPastry);
        }

        return friedPastries;
    }

    private static void validate(CreateOrderRequestDto dto) {
        if (dto == null) throw new BadRequestException("Create order request dto cannot be null");
        if (dto.getOrder().isEmpty()) throw new BadRequestException("Order cannot be empty");
    }
}
