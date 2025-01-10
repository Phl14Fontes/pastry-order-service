package com.mba.orderservice.infrastructure.adapter.out.dao;

import com.mba.orderservice.infrastructure.adapter.out.repository.entity.FriedPastryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriedPastryDao extends JpaRepository<FriedPastryEntity, Integer> {
}
