package br.dev.henriquealmeida.digimonwebapi.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record StandardErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
