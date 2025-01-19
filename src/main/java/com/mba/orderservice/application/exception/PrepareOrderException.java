package com.mba.orderservice.application.exception;

public class PrepareOrderException extends RuntimeException {
    private final String message;

    public PrepareOrderException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
