package com.weather.exception;

import com.weather.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void shouldReturnBadRequestErrorForWebClientBadRequestException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = globalExceptionHandler.BadRequest(mock(WebClientResponseException.BadRequest.class));

        assertEquals(BAD_REQUEST, errorResponseResponseEntity.getStatusCode());
    }

    @Test
    void shouldReturnForbiddenErrorForWebClientForbiddenException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = globalExceptionHandler.Forbidden(mock(WebClientResponseException.Forbidden.class));

        assertEquals(FORBIDDEN, errorResponseResponseEntity.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundErrorForWebClientNotFoundException() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = globalExceptionHandler.NotFound(mock(WebClientResponseException.NotFound.class));

        assertEquals(NOT_FOUND, errorResponseResponseEntity.getStatusCode());
    }
}