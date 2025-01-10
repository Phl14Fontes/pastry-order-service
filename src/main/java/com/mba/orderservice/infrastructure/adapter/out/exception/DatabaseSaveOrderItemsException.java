package com.mba.orderservice.infrastructure.adapter.out.exception;

public class DatabaseSaveOrderItemsException extends RuntimeException {
    private final String message;

    public DatabaseSaveOrderItemsException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
