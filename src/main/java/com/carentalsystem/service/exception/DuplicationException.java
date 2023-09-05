package com.carentalsystem.service.exception;

public class DuplicationException extends RuntimeException {
    public DuplicationException() {
        super();
    }

    public DuplicationException(String message) {
        super(message);
    }
}
