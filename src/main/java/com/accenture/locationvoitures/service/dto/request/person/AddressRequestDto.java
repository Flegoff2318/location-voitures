package com.accenture.locationvoitures.service.dto.request.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRequestDto(
        @NotBlank(message = "address.street.blank") String street,
        @NotBlank(message = "address.postalcode.blank") @Pattern(regexp = "^[0-9]{5}$",message = "address.postalcode.invalid") String postalCode,
        @NotBlank(message = "address.city.blank") String city
) {
}
