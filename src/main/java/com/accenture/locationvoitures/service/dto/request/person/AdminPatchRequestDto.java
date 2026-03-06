package com.accenture.locationvoitures.service.dto.request.person;

import jakarta.validation.constraints.Pattern;

public record AdminPatchRequestDto(
        String firstname,
        String lastname,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$") String password,
        String companyFunction
) {
}
