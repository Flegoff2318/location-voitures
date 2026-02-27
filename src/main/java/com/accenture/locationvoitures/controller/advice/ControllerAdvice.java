package com.accenture.locationvoitures.controller.advice;

import com.accenture.locationvoitures.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    /**
     * Called when any Business exception happens.
     * Returns an error with the business message.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDto> businessException(BusinessException e) {
        return ResponseEntity.status(e.getCode()).body(
                new ErrorDto(
                        LocalDateTime.now(),
                        e.getCode().value(),
                        e.getMessage()
                )
        );
    }
}
