package com.accenture.locationvoitures.controller.advice;

import com.accenture.locationvoitures.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
@AllArgsConstructor
public class ControllerAdvice {

    private final MessageSource messageSource;

    /**
     * Called when any Business exception happens.
     * Returns an error with the business message.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDto> businessException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ErrorDto(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ));
    }

    /**
     * Called when an entity is not found in the database.
     * Returns a 404 NOT_FOUND.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> entityNotFoundsException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(
                java.time.LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        ));
    }


    /**
     * Called when the received JSON is invalid or can't be converted.
     * Example : an expected integer typed field receives a string.
     * Returns a 400 BAD_REQUEST with an explicit message.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> notReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid JSON (Type Conversion Error) : " + e.getMessage()
        ));
    }

    /**
     * Called when the arguments validation (@Valid) fails.
     * Return a 400 BAD_REQUEST with the list of field errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorsDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorsDto errorsDto = new ErrorsDto(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getBindingResult().getAllErrors().stream()
                        .map(error -> new ErrorValidDto(
                                ((FieldError) error).getField(),
                                messageSource.getMessage(error.getDefaultMessage(), null, LocaleContextHolder.getLocale())
                        ))
                        .toList()
        );

        return ResponseEntity.badRequest().body(errorsDto);
    }

    /**
     * Called when a DateTimeParseException happens
     * Returns a generic 400 BAD_REQUEST with a date format hint
     * Temporary Exception Handler for the CustomerRequestDto & CustomerPatchRequestDto
     */
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorDto> dateTimeParseEx(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Date Format (YYYY-MM-DD)"
        ));
    }

    /**
     * Called in last resort for any exception that is not explicitly handled.
     * Returns a generic 400 BAD_REQUEST.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> ex(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ));
    }
}
