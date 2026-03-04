package com.accenture.locationvoitures.service.dto.request.person;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDto(
        @NotBlank String street,
        @NotBlank String postalCode,
        @NotBlank String city
) {
}
