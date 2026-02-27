package com.accenture.locationvoitures.exception;

import org.springframework.http.HttpStatus;


public class AdminException extends BusinessException {

    public AdminException(String message, HttpStatus code) {
        super(message, code);

    }
}
