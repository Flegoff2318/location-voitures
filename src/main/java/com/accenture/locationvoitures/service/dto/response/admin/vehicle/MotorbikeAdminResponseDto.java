package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.model.enumeration.EMotorbikeType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;

import java.util.UUID;

public record MotorbikeAdminResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        EDrivingLicence requiredDrivingLicence,
        VehicleMetaDataResponseDto vehicleMetaData,

        Integer weight,
        ETransmission transmission,
        Integer seatHeight,
        Integer power,
        Integer numberOfCylinders,
        Integer engineDisplacement,

        EMotorbikeType motorBikeType
) implements VehicleAdminResponseDto {
}
