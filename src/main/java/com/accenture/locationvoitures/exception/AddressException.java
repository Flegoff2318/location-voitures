package com.accenture.locationvoitures.exception;

import org.springframework.http.HttpStatus;

public class AddressException extends BusinessException {
    public AddressException(String message, HttpStatus code) {
        super(message, code);
    }
}
