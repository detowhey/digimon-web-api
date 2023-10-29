package br.dev.henriquealmeida.digimonwebapi.exception;

public class InvalidDigimonNameException extends RuntimeException {

    public InvalidDigimonNameException(String message) {
        super(message);
    }
}
