package com.accenture.locationvoitures.service.dto.response.customer;

import java.util.UUID;

public sealed interface VehicleCustomerResponseDto permits FourWheeledCustomerResponseDto{
    UUID uuid();
    String brand();
    String model();
    String color();
    String requiredDrivingLicence();
}
