package com.mba.orderservice.infrastructure.adapter.out.dao;

import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepositoryDao extends JpaRepository<OrderEntity, Integer> {

    OrderEntity findByCorrelationId(String correlationId);

    @Modifying
    @Transactional
    @Query("UPDATE orders o SET o.status = :status WHERE o.correlationId = :correlationId")
    void updateStatusByCorrelationId(OrderStatus status, String correlationId);
}
