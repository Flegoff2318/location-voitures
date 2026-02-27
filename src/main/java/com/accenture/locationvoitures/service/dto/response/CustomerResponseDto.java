package com.accenture.locationvoitures.service.dto.response;

import java.util.List;
import java.util.UUID;

public record CustomerResponseDto(
        UUID uuid,//Questionable
        String firstname,
        String lastname,
        String street,
        String postalCode,
        String city,
        String email,
        String birthday,
        List<String> drivingLicences
) {
}
