package com.accenture.locationvoitures.service.dto.response.admin;

import java.util.UUID;

public sealed interface VehicleAdminResponseDto permits FourWheeledAdminResponseDto {
    UUID uuid();
    String brand();
    String model();
    String color();

    String requiredDrivingLicence();
    VehicleMetaDataResponseDto vehicleMetaData();
}
