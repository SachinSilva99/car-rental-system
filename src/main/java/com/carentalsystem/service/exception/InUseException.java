package com.carentalsystem.service.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }

    public InUseException() {
    }
}
