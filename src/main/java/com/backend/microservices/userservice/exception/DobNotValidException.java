package com.backend.microservices.userservice.exception;

public class DobNotValidException extends RuntimeException {
    public DobNotValidException(String dobNotValid) {
        super(dobNotValid);
    }
}
