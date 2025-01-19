package com.mba.orderservice.infrastructure.adapter.out.exception;

public class DatabaseUpdateOrderStatusException extends RuntimeException {
    private final String message;

    public DatabaseUpdateOrderStatusException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
