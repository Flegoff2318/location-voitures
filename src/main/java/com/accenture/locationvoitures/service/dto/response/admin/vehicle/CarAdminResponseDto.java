package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import com.accenture.locationvoitures.model.enumeration.ECarType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;

import java.util.UUID;

public record CarAdminResponseDto(
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

        Integer numberOfLuggage,
        ECarType carType
) implements FourWheeledAdminResponseDto {
}
