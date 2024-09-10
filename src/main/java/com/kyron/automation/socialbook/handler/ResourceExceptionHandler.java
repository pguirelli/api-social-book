package com.kyron.automation.socialbook.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kyron.automation.socialbook.model.ErrorDetails;
import com.kyron.automation.socialbook.services.exceptions.AuthorAlreadyExistsException;
import com.kyron.automation.socialbook.services.exceptions.AuthorNotFoundException;
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

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleAuthorNotFoundException(AuthorNotFoundException e,
            HttpServletRequest request) {

        ErrorDetails error = new ErrorDetails();
        error.setStatus(404l);
        error.setTitle("This author was not found.");
        error.setDeveloperMessage("http://error.socialbooks.com/404");
        error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleAuthorAlreadyExistsException(AuthorAlreadyExistsException e,
            HttpServletRequest request) {

        ErrorDetails error = new ErrorDetails();
        error.setStatus(409l);
        error.setTitle("This author already exists.");
        error.setDeveloperMessage("http://error.socialbooks.com/409");
        error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException e,
            HttpServletRequest request) {

        ErrorDetails error = new ErrorDetails();
        error.setStatus(400l);
        error.setTitle("Invalid request.");
        error.setDeveloperMessage("http://error.socialbooks.com/400");
        error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
