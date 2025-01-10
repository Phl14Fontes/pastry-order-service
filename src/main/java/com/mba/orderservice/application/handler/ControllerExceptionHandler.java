package com.mba.orderservice.application.handler;

import com.mba.orderservice.application.exception.BadRequestException;
import com.mba.orderservice.infrastructure.adapter.out.exception.DatabaseSaveOrderException;
import com.mba.orderservice.infrastructure.adapter.out.exception.DatabaseSaveOrderItemsException;
import com.mba.orderservice.infrastructure.adapter.out.exception.NullFieldException;
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

    @ExceptionHandler({
            NullFieldException.class,
            DatabaseSaveOrderException.class,
            DatabaseSaveOrderItemsException.class
    })
    public ResponseEntity<String> handleInternalServerError(RuntimeException ex) {
        String errorMessage = ex.getMessage();

        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class.getName());
}
