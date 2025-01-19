package com.mba.orderservice.infrastructure.adapter.out.repository;

import com.mba.orderservice.domain.enums.OrderStatus;
import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.domain.repository.OrderRepository;
import com.mba.orderservice.infrastructure.adapter.mapper.EntityModelMapper;
import com.mba.orderservice.infrastructure.adapter.mapper.ModelEntityMapper;
import com.mba.orderservice.infrastructure.adapter.out.dao.OrderRepositoryDao;
import com.mba.orderservice.infrastructure.adapter.out.exception.DatabaseGetOrderException;
import com.mba.orderservice.infrastructure.adapter.out.exception.DatabaseSaveOrderException;
import com.mba.orderservice.infrastructure.adapter.out.exception.DatabaseUpdateOrderStatusException;
import com.mba.orderservice.infrastructure.adapter.out.exception.NullFieldException;
import com.mba.orderservice.infrastructure.adapter.out.repository.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryDao dao;

    @Override
    public Order create(Order order) {
        if (order.getCorrelationId() == null) throw new NullFieldException("Correlation Id cannot be null");

        OrderEntity entity = ModelEntityMapper.orderToEntity(order);
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

    @Override
    public List<Order> getAll() {
        try {
            List<OrderEntity> orderEntities = dao.findAll();
            return EntityModelMapper.entityOrdersListToModelList(orderEntities);
        } catch (Throwable ex) {
            logger.error("Something are wrong when try to get all orders on database. Error: [{}]", ex.getMessage());
            throw new DatabaseGetOrderException(ex.getMessage());
        }
    }

    @Override
    public Order getBy(String correlationId) {
        try {
            OrderEntity entity = dao.findByCorrelationId(correlationId);

            return ModelEntityMapper.entityToOrder(entity);
        } catch (Throwable ex) {
            logger.error("Something are wrong when try to get order by correlation id [{}] on database. Error: [{}]", correlationId, ex.getMessage());
            throw new DatabaseGetOrderException(ex.getMessage());
        }
    }

    @Override
    public void updateStatusByCorrelationId(OrderStatus newStatus, String correlationId) {
        try {
            dao.updateStatusByCorrelationId(newStatus, correlationId);
        } catch (Throwable ex) {
            logger.error("Something are wrong when try to update order status by correlation id [{}] on database. Error: [{}]", correlationId, ex.getMessage());
            throw new DatabaseUpdateOrderStatusException(ex.getMessage());
        }
    }

    private static final Logger logger = LogManager.getLogger(OrderRepositoryImpl.class.getName());
}
