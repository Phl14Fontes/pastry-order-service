package com.mba.orderservice.application.handler;

import com.mba.orderservice.application.exception.BadRequestException;
import com.mba.orderservice.application.exception.OrderNotFoundException;
import com.mba.orderservice.application.exception.UnprocessableEntityException;
import com.mba.orderservice.infrastructure.adapter.out.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(RuntimeException ex) {
        String errorMessage = ex.getMessage();

        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleNotFound(RuntimeException ex) {
        String errorMessage = ex.getMessage();

        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<String> handleUnprocessableEntity(RuntimeException ex) {
        String errorMessage = ex.getMessage();

        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @ExceptionHandler({
            NullFieldException.class,
            DatabaseSaveOrderException.class,
            DatabaseSaveOrderItemsException.class,
            DatabaseGetOrderException.class,
            DatabaseGetOrderItemsException.class,
            DatabaseUpdateOrderStatusException.class
    })
    public ResponseEntity<String> handleInternalServerError(RuntimeException ex) {
        String errorMessage = ex.getMessage();

        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class.getName());
}
