package com.mba.orderservice.application.service;

import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.repository.FriedPastryRepository;
import com.mba.orderservice.infrastructure.adapter.out.exception.NullFieldException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriedPastryService {

    private final FriedPastryRepository repository;

    public void createOrderItems(List<FriedPastry> friedPastries, String correlationId) {
        validate(friedPastries, correlationId);

        repository.saveOrderItems(friedPastries, correlationId);
    }

    private void validate(List<FriedPastry> friedPastries, String correlationId) {
        if (friedPastries == null || friedPastries.isEmpty()) throw new NullFieldException("Fried pastries list cannot be null or empty");
        if (correlationId == null) throw new NullFieldException("Correlation Id cannot be null");
    }
}
