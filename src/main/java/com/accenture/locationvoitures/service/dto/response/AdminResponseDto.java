package com.accenture.locationvoitures.service.dto.response;

import java.util.UUID;

public record AdminResponseDto(
        UUID uuid,
        String firstname,
        String lastname,
        String email,
        String companyFunction
) {
}
