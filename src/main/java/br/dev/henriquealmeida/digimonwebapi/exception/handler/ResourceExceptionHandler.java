package br.dev.henriquealmeida.digimonwebapi.exception.handler;

import br.dev.henriquealmeida.digimonwebapi.exception.InvalidDigimonLevelException;
import br.dev.henriquealmeida.digimonwebapi.exception.error.StandardErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidDigimonLevelException.class)
    public ResponseEntity<StandardErrorResponse> digimonLevelNotFond(InvalidDigimonLevelException e, ServletServerHttpRequest request) {
        return createResponseError(HttpStatus.NOT_FOUND, "Not found", e, request);
    }

    private ResponseEntity<StandardErrorResponse> createResponseError(HttpStatus httpStatus, String error, Exception exception, ServletServerHttpRequest request) {
        StandardErrorResponse errorResponse = new StandardErrorResponse(
                Instant.now(),
                httpStatus.value(),
                error,
                exception.getMessage(),
                request.getURI().toString()
        );
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
