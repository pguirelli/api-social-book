package com.kyron.automation.socialbook.services.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }
}
