package com.kyron.automation.socialbook.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kyron.automation.socialbook.model.ErrorDetails;
import com.kyron.automation.socialbook.services.exceptions.BookNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleBookNotFoundException(BookNotFoundException e,
            HttpServletRequest request) {

        ErrorDetails error = new ErrorDetails();
        error.setStatus(404l);
        error.setTitle("This book was not found.");
        error.setDeveloperMessage("http://error.socialbooks.com/404");
        error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
