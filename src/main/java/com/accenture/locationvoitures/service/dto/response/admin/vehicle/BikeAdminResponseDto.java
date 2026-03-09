package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import com.accenture.locationvoitures.model.enumeration.EBikeType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;

import java.util.UUID;

public record BikeAdminResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        EDrivingLicence requiredDrivingLicence,
        VehicleMetaDataResponseDto vehicleMetaData,

        Integer weight,
        Integer frameSize,
        Boolean discBrakes,

        Boolean isElectric,
        Integer batteryCapacity,
        Double autonomy,

        EBikeType bikeType
) implements VehicleAdminResponseDto {
}
