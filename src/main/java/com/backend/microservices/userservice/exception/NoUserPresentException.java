package com.backend.microservices.userservice.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;

public class NoUserPresentException extends Exception {
    public NoUserPresentException(String noUserPresentMessage) {
        super(noUserPresentMessage);
    }
}
