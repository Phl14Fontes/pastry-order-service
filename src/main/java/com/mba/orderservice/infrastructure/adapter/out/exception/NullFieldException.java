package com.mba.orderservice.infrastructure.adapter.out.exception;

public class NullFieldException extends RuntimeException {
    private final String message;

    public NullFieldException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
