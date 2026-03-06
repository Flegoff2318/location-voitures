package com.accenture.locationvoitures.service.dto.request.person.patch;

import com.accenture.locationvoitures.service.dto.request.person.AddressRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CustomerPatchRequestDto(
        String firstname,
        String lastname,
        @Valid AddressRequestDto address,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$") String password,
        String birthday,
        List<String> drivingLicences
) {
}
