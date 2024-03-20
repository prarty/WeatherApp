package com.weather.exception;

import com.weather.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.BadRequest.class)
    public ResponseEntity<ErrorResponse> BadRequest(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "An error occurred: " + ex.getMessage()));
    }

    @ExceptionHandler(WebClientResponseException.Forbidden.class)
    public ResponseEntity<ErrorResponse> Forbidden(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "An error occurred: " + ex.getMessage()));
    }

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ResponseEntity<ErrorResponse> NotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "An error occurred: " + ex.getMessage()));
    }

}