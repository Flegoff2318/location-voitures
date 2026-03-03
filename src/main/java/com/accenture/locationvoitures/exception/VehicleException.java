package com.accenture.locationvoitures.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VehicleException extends RuntimeException {
    private final HttpStatus code;
    public VehicleException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
