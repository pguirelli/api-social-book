package com.kyron.automation.socialbook.services.exceptions;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException() {
        super();
    }

    public AuthorNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }
}
