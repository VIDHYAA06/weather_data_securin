package com.securin.weatherdata.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CSVProcessingException.class)
    public ResponseEntity<String> handleCSVException(CSVProcessingException ex) {
        return ResponseEntity
                .badRequest()
                .body("CSV Processing Failed: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body("Something went wrong: " + ex.getMessage());
    }
}