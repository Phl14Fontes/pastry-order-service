package com.mba.orderservice.infrastructure.adapter.out.dao;

import com.mba.orderservice.infrastructure.adapter.out.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryDao extends JpaRepository<OrderEntity, Integer> {
}
