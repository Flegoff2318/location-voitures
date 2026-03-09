package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;

import java.util.UUID;

public sealed interface VehicleAdminResponseDto permits FourWheeledAdminResponseDto, MotorbikeAdminResponseDto, BikeAdminResponseDto {
    UUID uuid();
    String brand();
    String model();
    String color();

    EDrivingLicence requiredDrivingLicence();
    VehicleMetaDataResponseDto vehicleMetaData();
}
