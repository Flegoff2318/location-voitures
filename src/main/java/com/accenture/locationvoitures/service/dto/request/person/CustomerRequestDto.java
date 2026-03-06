package com.accenture.locationvoitures.service.dto.request.person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CustomerRequestDto(
        @NotBlank String firstname,
        @NotBlank String lastname,
        @Valid AddressRequestDto address,
        @NotBlank @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") String email,
        @NotBlank @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$") String password,
        @NotBlank String birthday,
        List<String> drivingLicences
) {
}
