package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import java.util.UUID;

public sealed interface VehicleAdminResponseDto permits FourWheeledAdminResponseDto, MotorbikeAdminResponseDto, BikeAdminResponseDto {
    UUID uuid();
    String brand();
    String model();
    String color();

    String requiredDrivingLicence();
    VehicleMetaDataResponseDto vehicleMetaData();
}
