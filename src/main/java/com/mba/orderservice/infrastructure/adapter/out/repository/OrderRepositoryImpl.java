package com.mba.orderservice.infrastructure.adapter.out.repository;

import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.domain.repository.OrderRepository;
import com.mba.orderservice.infrastructure.adapter.mapper.ModelEntityMapper;
import com.mba.orderservice.infrastructure.adapter.out.dao.OrderRepositoryDao;
import com.mba.orderservice.infrastructure.adapter.out.exception.DatabaseSaveOrderException;
import com.mba.orderservice.infrastructure.adapter.out.exception.NullFieldException;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryDao dao;

    @Override
    public Order create(Order order) {
        if (order.getCorrelationId() == null) throw new NullFieldException("Correlation Id cannot be null");

        OrderEntity entity = ModelEntityMapper.orderModelToEntity(order);
        entity.setCreatedAt(LocalDateTime.now());

        try {
            dao.save(entity);
            logger.info("Order saved with success: [{}]", order);
        } catch (Throwable ex) {
            logger.error("Something are wrong when try to save order on database. Error: [{}]", ex.getMessage());
            throw new DatabaseSaveOrderException(ex.getMessage());
        }

        return order;
    }

    private static final Logger logger = LogManager.getLogger(OrderRepositoryImpl.class.getName());
}
