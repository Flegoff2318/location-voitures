package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import java.util.UUID;

public record UtilityAdminResponseDto(
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

        Double haulingCapacity,
        Double ptac,

        String utilityType
) implements FourWheeledAdminResponseDto {
}
