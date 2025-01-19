package com.mba.orderservice.infrastructure.adapter.mapper;

import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.FriedPastryEntity;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityModelMapper {

    public static List<Order> entityOrdersListToModelList(List<OrderEntity> entities) {
        if (entities.isEmpty()) return List.of();

        return entities.stream()
                .map(entity -> {
                    if (entity.getUpdatedAt() == null) {
                        return new Order(
                                entity.getCorrelationId(),
                                entity.getName(),
                                entity.getPaymentMethod(),
                                entity.getStatus(),
                                new ArrayList<>(),
                                entity.getTotalAmount(),
                                entity.getCreatedAt()
                        );
                    } else {
                        return Order.builder()
                                .correlationId(entity.getCorrelationId())
                                .name(entity.getName())
                                .paymentMethod(entity.getPaymentMethod())
                                .status(entity.getStatus())
                                .friedPastries(new ArrayList<>())
                                .totalAmount(entity.getTotalAmount())
                                .createdAt(entity.getCreatedAt())
                                .updatedAt(entity.getUpdatedAt())
                                .build();
                    }
                })
                .collect(Collectors.toList());
    }


    public static List<FriedPastry> entityOrderItemsListToModelList(List<FriedPastryEntity> entities) {
        if (entities.isEmpty()) return List.of();

        return entities.stream()
                .map(entity -> {
                    return new FriedPastry(
                            entity.getFlavor(),
                            entity.getQuantity(),
                            entity.getCorrelationId()
                    );
                })
                .collect(Collectors.toList());
    }
}
