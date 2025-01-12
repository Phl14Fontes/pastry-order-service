package com.mba.orderservice.infrastructure.adapter.mapper;

import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.FriedPastryEntity;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class ModelEntityMapper {

    public static OrderEntity orderToEntity(Order order) {
        return new OrderEntity(
                order.getName(),
                order.getCorrelationId(),
                order.getPaymentMethod(),
                order.getStatus(),
                order.getTotalAmount()
        );
    }

    public static Order entityToOrder(OrderEntity entity) {
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
            return new Order(
                    entity.getCorrelationId(),
                    entity.getName(),
                    entity.getPaymentMethod(),
                    entity.getStatus(),
                    new ArrayList<>(),
                    entity.getTotalAmount(),
                    entity.getCreatedAt(),
                    entity.getUpdatedAt()
            );
        }
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
