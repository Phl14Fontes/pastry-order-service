package com.mba.orderservice.infrastructure.adapter.in.rest;

import com.mba.orderservice.application.dto.request.CreateOrderRequestDto;
import com.mba.orderservice.application.dto.response.CreateOrderResponseDto;
import com.mba.orderservice.application.mapper.DtoModelMapper;
import com.mba.orderservice.application.port.in.CreateOrderUseCase;
import com.mba.orderservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto request) {
        Order order = DtoModelMapper.toCreateOrderModel(request);

        String correlationId = createOrderUseCase.createOrder(order);

        CreateOrderResponseDto response = new CreateOrderResponseDto(correlationId);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }
}
