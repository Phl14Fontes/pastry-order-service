package com.mba.orderservice.infrastructure.adapter.out.dao;

import com.mba.orderservice.infrastructure.adapter.out.repository.entity.FriedPastryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriedPastryDao extends JpaRepository<FriedPastryEntity, Integer> {

    List<FriedPastryEntity> findByCorrelationId(String correlationId);
}
