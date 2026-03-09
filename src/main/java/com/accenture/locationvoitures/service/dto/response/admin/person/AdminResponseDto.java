package com.accenture.locationvoitures.service.dto.response.admin.person;

import java.util.UUID;

public record AdminResponseDto(
        UUID uuid,
        String firstname,
        String lastname,
        String email,
        String companyFunction,
        String role
) {
}
