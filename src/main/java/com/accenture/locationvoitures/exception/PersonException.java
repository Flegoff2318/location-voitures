package com.accenture.locationvoitures.exception;

import org.springframework.http.HttpStatus;


public class PersonException extends BusinessException {
    public PersonException(String message, HttpStatus code) {
        super(message, code);

    }
}
