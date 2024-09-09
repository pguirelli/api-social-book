package com.kyron.automation.socialbook.services.exceptions;

public class AuthorAlreadyExistsException extends RuntimeException {

    public AuthorAlreadyExistsException() {
        super();
    }

    public AuthorAlreadyExistsException(String message, Throwable reason) {
        super(message, reason);
    }

}
