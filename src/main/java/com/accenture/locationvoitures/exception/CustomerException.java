package com.accenture.locationvoitures.exception;

import org.springframework.http.HttpStatus;

public class CustomerException extends BusinessException {

    public CustomerException(String message, HttpStatus code) {
        super(message, code);
    }
}
