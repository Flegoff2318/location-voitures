package com.accenture.locationvoitures.service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDto(
        @NotBlank String street,
        @NotBlank String postalCode,
        @NotBlank String city
) {
}
