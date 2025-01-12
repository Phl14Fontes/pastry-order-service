package com.mba.orderservice.infrastructure.adapter.out.exception;

public class DatabaseGetOrderItemsException extends RuntimeException {
    private final String message;

    public DatabaseGetOrderItemsException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
