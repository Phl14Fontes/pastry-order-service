package com.mba.orderservice.infrastructure.adapter.mapper;

import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.FriedPastryEntity;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class ModelEntityMapper {

    public static OrderEntity orderModelToEntity(Order order) {
        return new OrderEntity(
                order.getName(),
                order.getCorrelationId(),
                order.getPaymentMethod(),
                order.getStatus(),
                order.getTotalAmount()
        );
    }

    public static List<FriedPastryEntity> friedPastriesModelToEntityList(List<FriedPastry> friedPastries, String correlationId) {
        return friedPastries.stream()
                .map(friedPastry -> FriedPastryEntity.builder()
                        .correlationId(correlationId)
                        .flavor(friedPastry.getFlavor())
                        .quantity(friedPastry.getQuantity())
                        .amount(friedPastry.getAmount())
                        .observations(friedPastry.getObservations())
                        .build()
                )
                .toList();
    }

}
