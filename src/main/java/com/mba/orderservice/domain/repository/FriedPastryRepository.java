package com.mba.orderservice.domain.repository;

import com.mba.orderservice.domain.model.FriedPastry;

import java.util.List;

public interface FriedPastryRepository {
    List<FriedPastry> saveOrderItems(List<FriedPastry> friedPastries, String correlationId);
}
