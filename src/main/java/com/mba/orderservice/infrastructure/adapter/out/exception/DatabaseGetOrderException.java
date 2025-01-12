package com.mba.orderservice.infrastructure.adapter.out.exception;

public class DatabaseGetOrderException extends RuntimeException {
    private final String message;

    public DatabaseGetOrderException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
