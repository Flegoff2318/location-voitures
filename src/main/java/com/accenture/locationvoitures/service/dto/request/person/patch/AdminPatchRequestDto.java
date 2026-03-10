package com.accenture.locationvoitures.service.dto.request.person.patch;

import jakarta.validation.constraints.Pattern;

public record AdminPatchRequestDto(
        String firstname,
        String lastname,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$",message = "person.password.invalid") String password,
        String companyFunction
) {
}
