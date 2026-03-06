package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import java.util.UUID;

public record MotorbikeAdminResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        String requiredDrivingLicence,
        VehicleMetaDataResponseDto vehicleMetaData,

        Integer weight,
        String transmission,
        Integer seatHeight,
        Integer power,
        Integer numberOfCylinders,
        Integer engineDisplacement,

        String motorBikeType
) implements VehicleAdminResponseDto {
}
