package com.accenture.locationvoitures.service.dto.response.customer.person;

import com.accenture.locationvoitures.service.dto.response.customer.AddressResponseDto;

import java.util.List;
import java.util.UUID;

public record CustomerResponseDto(
        UUID uuid,//Questionable
        String firstname,
        String lastname,
        AddressResponseDto address,
        String email,
        String birthday,
        List<String> drivingLicences
) {
}
