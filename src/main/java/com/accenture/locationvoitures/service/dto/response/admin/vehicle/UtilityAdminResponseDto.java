package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
import com.accenture.locationvoitures.model.enumeration.EUtilityType;

import java.util.UUID;

public record UtilityAdminResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        EDrivingLicence requiredDrivingLicence,
        VehicleMetaDataResponseDto vehicleMetaData,

        Integer numberOfSeats,
        EFuelType fuelType,
        ETransmission transmission,
        Boolean airConditioning,

        Double haulingCapacity,
        Double ptac,

        EUtilityType utilityType
) implements FourWheeledAdminResponseDto {
}
