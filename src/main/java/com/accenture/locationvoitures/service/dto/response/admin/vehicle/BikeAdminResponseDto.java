package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import java.util.UUID;

public record BikeAdminResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        String requiredDrivingLicence,
        VehicleMetaDataResponseDto vehicleMetaData,

        Integer weight,
        Integer frameSize,
        Boolean discBrakes,

        Boolean isElectric,
        Integer batteryCapacity,
        Double autonomy,

        String bikeType
) implements VehicleAdminResponseDto {
}
