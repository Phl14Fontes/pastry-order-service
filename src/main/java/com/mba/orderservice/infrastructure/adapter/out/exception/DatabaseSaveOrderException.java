package com.mba.orderservice.infrastructure.adapter.out.exception;

public class DatabaseSaveOrderException extends RuntimeException {
    private final String message;

    public DatabaseSaveOrderException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
