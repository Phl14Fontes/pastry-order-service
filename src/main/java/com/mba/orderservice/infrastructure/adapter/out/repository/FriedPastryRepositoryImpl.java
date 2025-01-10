package com.mba.orderservice.infrastructure.adapter.out.repository;

import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.repository.FriedPastryRepository;
import com.mba.orderservice.infrastructure.adapter.mapper.ModelEntityMapper;
import com.mba.orderservice.infrastructure.adapter.out.dao.FriedPastryDao;
import com.mba.orderservice.infrastructure.adapter.out.exception.DatabaseSaveOrderException;
import com.mba.orderservice.infrastructure.adapter.out.exception.DatabaseSaveOrderItemsException;
import com.mba.orderservice.infrastructure.adapter.out.exception.NullFieldException;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.FriedPastryEntity;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriedPastryRepositoryImpl implements FriedPastryRepository {

    private final FriedPastryDao dao;

    @Override
    public List<FriedPastry> saveOrderItems(List<FriedPastry> friedPastries, String correlationId) {
        List<FriedPastryEntity> entities = ModelEntityMapper.friedPastriesModelToEntityList(friedPastries, correlationId);

        try {
            dao.saveAll(entities);
            logger.info("Order items saved with success: [{}]", entities);
        } catch (Throwable ex) {
            logger.error("Something are wrong when try to save order items on database. Error: [{}]", ex.getMessage());
            throw new DatabaseSaveOrderItemsException(ex.getMessage());
        }

        return friedPastries;
    }

    private static final Logger logger = LogManager.getLogger(FriedPastryRepositoryImpl.class.getName());
}
