package com.accenture.locationvoitures.controller.advice;

import java.time.LocalDateTime;

public record ErrorDto(LocalDateTime timestamp, int errorCode, String errorMessage) {
}
