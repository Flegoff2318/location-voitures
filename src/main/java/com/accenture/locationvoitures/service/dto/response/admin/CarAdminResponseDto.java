package com.accenture.locationvoitures.service.dto.response.admin;

import java.util.UUID;

public record CarAdminResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        String requiredDrivingLicence,
        VehicleMetaDataResponseDto vehicleMetaData,
        Integer numberOfSeats,
        String fuelType,
        String transmission,
        Boolean airConditioning,
        Integer numberOfLuggage,
        String carType
) implements FourWheeledAdminResponseDto {
}
