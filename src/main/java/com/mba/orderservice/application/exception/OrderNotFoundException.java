package com.mba.orderservice.application.exception;

public class OrderNotFoundException extends RuntimeException {
    private final String message;

    public OrderNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
