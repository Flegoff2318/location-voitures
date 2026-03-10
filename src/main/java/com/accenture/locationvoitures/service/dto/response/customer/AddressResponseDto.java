package com.accenture.locationvoitures.service.dto.response.customer;

public record AddressResponseDto(
        String street,
        String postalCode,
        String city
) {
}
