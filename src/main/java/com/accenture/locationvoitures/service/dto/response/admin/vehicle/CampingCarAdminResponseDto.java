package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import com.accenture.locationvoitures.service.dto.response.CampingCarEquipmentResponseDto;

import java.util.UUID;

public record CampingCarAdminResponseDto(
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

        Integer ptac,
        Double height,
        CampingCarEquipmentResponseDto campingCarEquipment,

        String campingCarType
) implements FourWheeledAdminResponseDto {
}
