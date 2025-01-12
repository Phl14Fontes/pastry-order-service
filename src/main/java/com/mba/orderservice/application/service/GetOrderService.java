package com.mba.orderservice.application.service;

import com.mba.orderservice.application.port.in.GetOrdersUseCase;
import com.mba.orderservice.domain.model.FriedPastry;
import com.mba.orderservice.domain.model.Order;
import com.mba.orderservice.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetOrderService implements GetOrdersUseCase {

    private final OrderRepository repository;
    private final FriedPastryService friedPastryService;

    @Override
    public List<Order> getAll() {
        List<FriedPastry> friedPastries = friedPastryService.getAll();

        List<Order> orders = repository.getAll();

        return mapFriedPastriesToCorrelationOrders(friedPastries, orders);
    }

    @Override
    public Order getBy(String correlationId) {
        List<FriedPastry> friedPastries = friedPastryService.getBy(correlationId);

        Order order = repository.getBy(correlationId);

        return mapFriedPastriesToOrder(friedPastries, order);
    }

    private List<Order> mapFriedPastriesToCorrelationOrders(List<FriedPastry> friedPastries, List<Order> orders) {
        if (friedPastries.isEmpty() && orders.isEmpty()) return orders;
        if (friedPastries.isEmpty()) return orders;

        Map<String, List<FriedPastry>> pastriesByCorrelationId = friedPastries.stream()
                .collect(Collectors.groupingBy(FriedPastry::getCorrelationId));

        return orders.stream()
                .map(order -> {
                    List<FriedPastry> friedPastriesByCorrelationId = pastriesByCorrelationId.getOrDefault(order.getCorrelationId(), List.of());

                    return new Order(
                            order.getCorrelationId(),
                            order.getName(),
                            order.getPaymentMethod(),
                            order.getStatus(),
                            friedPastriesByCorrelationId,
                            order.getTotalAmount(),
                            order.getCreatedAt(),
                            order.getUpdatedAt()
                    );
                })
                .sorted(Comparator.comparing(Order::getCreatedAt))
                .collect(Collectors.toList());
    }

    private Order mapFriedPastriesToOrder(List<FriedPastry> friedPastries, Order order) {
        if (order != null && friedPastries != null && !friedPastries.isEmpty()) {
            order.setFriedPastries(friedPastries);
        }

        return order;
    }
}
