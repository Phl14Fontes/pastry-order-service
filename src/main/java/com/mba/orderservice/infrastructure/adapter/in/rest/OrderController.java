package com.mba.orderservice.infrastructure.adapter.in.rest;

import com.mba.orderservice.application.dto.request.CreateOrderRequestDto;
import com.mba.orderservice.application.dto.request.UpdateOrderStatusRequestDto;
import com.mba.orderservice.application.dto.response.AllOrdersResponseDto;
import com.mba.orderservice.application.dto.response.CreateOrderResponseDto;
import com.mba.orderservice.application.dto.response.OrderResponseDto;
import com.mba.orderservice.application.exception.OrderNotFoundException;
import com.mba.orderservice.application.mapper.DtoToModelMapper;
import com.mba.orderservice.application.mapper.ModelToDtoMapper;
import com.mba.orderservice.application.port.in.CreateOrderUseCase;
import com.mba.orderservice.application.port.in.GetOrdersUseCase;
import com.mba.orderservice.application.port.in.UpdateOrderStatusUseCase;
import com.mba.orderservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createUseCase;
    private final GetOrdersUseCase getUseCase;
    private final UpdateOrderStatusUseCase updateStatusUseCase;

    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> create(@RequestBody CreateOrderRequestDto request) {
        logger.info("Request to create order received: [{}]", request);

        Order order = DtoToModelMapper.toCreateOrderModel(request);

        Order createdOrder = createUseCase.createOrder(order);

        CreateOrderResponseDto response = new CreateOrderResponseDto(
                createdOrder.getCorrelationId(),
                createdOrder.getName(),
                createdOrder.getStatus()
        );

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AllOrdersResponseDto>> getAll() {
        List<Order> orders = getUseCase.getAll();

        if (orders.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();

        List<AllOrdersResponseDto> response = ModelToDtoMapper.fromOrders(orders);

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @GetMapping("/{correlationId}")
    public ResponseEntity<OrderResponseDto> findByCorrelationId(@PathVariable String correlationId) {
        Order order = getUseCase.getBy(correlationId);

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();
        }

        OrderResponseDto response = ModelToDtoMapper.fromOrder(order);

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PatchMapping
    public ResponseEntity<Void> updateStatus(@RequestBody UpdateOrderStatusRequestDto request) {
        logger.info("Request to update order status received: [{}]", request);

        try {
            updateStatusUseCase.updateStatus(request.getStatus(), request.getCorrelationId());
        } catch (OrderNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();
        }

        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    @DeleteMapping("/{correlationId}")
    public ResponseEntity<Void> cancel(@PathVariable String correlationId) {
        try {
            updateStatusUseCase.cancelOrder(correlationId);
        } catch (OrderNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();
        }

        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    private static final Logger logger = LogManager.getLogger(OrderController.class.getName());
}
