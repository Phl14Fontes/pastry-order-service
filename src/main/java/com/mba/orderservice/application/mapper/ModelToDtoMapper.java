package com.mba.orderservice.application.mapper;

import com.mba.orderservice.application.dto.AvailableFriedPastryDto;
import com.mba.orderservice.application.dto.response.AllOrdersResponseDto;
import com.mba.orderservice.application.dto.response.OrderResponseDto;
import com.mba.orderservice.domain.enums.Flavor;
import com.mba.orderservice.domain.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class ModelToDtoMapper {

    public static List<AllOrdersResponseDto> fromOrders(List<Order> orders) {
        return orders.stream()
                .map(order -> {
                    if (order.getUpdatedAt() == null) {
                        return new AllOrdersResponseDto(
                                order.getCorrelationId(),
                                order.getName(),
                                order.getStatus(),
                                order.getFriedPastries(),
                                order.getTotalAmount(),
                                order.getCreatedAt()
                        );
                    } else {
                        return new AllOrdersResponseDto(
                                order.getCorrelationId(),
                                order.getName(),
                                order.getStatus(),
                                order.getFriedPastries(),
                                order.getTotalAmount(),
                                order.getCreatedAt(),
                                order.getUpdatedAt()
                        );
                    }
                })
                .collect(Collectors.toList());
    }

    public static OrderResponseDto fromOrder(Order order) {
        if (order.getUpdatedAt() == null) {
            return new OrderResponseDto(
                    order.getName(),
                    order.getPaymentMethod(),
                    order.getStatus(),
                    order.getFriedPastries(),
                    order.getTotalAmount(),
                    order.getCreatedAt()
            );
        }

        return new OrderResponseDto(
                order.getName(),
                order.getPaymentMethod(),
                order.getStatus(),
                order.getFriedPastries(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

    public static List<AvailableFriedPastryDto> fromFlavors(List<Flavor> flavors) {
        return flavors.stream()
                .map(flavor ->
                        new AvailableFriedPastryDto(
                                flavor,
                                flavor.getUnitPrice(),
                                flavor.getDescription()
                        )
                )
                .collect(Collectors.toList());
    }
}
