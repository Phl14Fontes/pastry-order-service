package com.mba.orderservice.infrastructure.adapter.out.repository;

import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.repository.FriedPastryRepository;
import com.mba.orderservice.infrastructure.adapter.mapper.EntityModelMapper;
import com.mba.orderservice.infrastructure.adapter.mapper.ModelEntityMapper;
import com.mba.orderservice.infrastructure.adapter.out.dao.FriedPastryDao;
import com.mba.orderservice.infrastructure.adapter.out.exception.*;
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

    public List<FriedPastry> getAll() {
        try {
            List<FriedPastryEntity> entities =  dao.findAll();
            return EntityModelMapper.entityOrderItemsListToModelList(entities);
        } catch (Throwable ex) {
            logger.error("Something are wrong when try to get all order items from database. Error: [{}]", ex.getMessage());
            throw new DatabaseGetOrderItemsException(ex.getMessage());
        }
    }

    @Override
    public List<FriedPastry> getBy(String correlationId) {
        try {
            List<FriedPastryEntity> entities = dao.findByCorrelationId(correlationId);
            return EntityModelMapper.entityOrderItemsListToModelList(entities);
        } catch (Throwable ex) {
            logger.error("Something are wrong when try to get order items by correlation id [{}] on database. Error: [{}]", correlationId, ex.getMessage());
            throw new DatabaseGetOrderItemsException(ex.getMessage());
        }
    }

    private static final Logger logger = LogManager.getLogger(FriedPastryRepositoryImpl.class.getName());
}
