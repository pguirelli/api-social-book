package com.kyron.automation.socialbook.services.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }
}
