package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import com.accenture.locationvoitures.model.enumeration.EBikeType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;

import java.util.UUID;

public record BikeCustomerResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        EDrivingLicence requiredDrivingLicence,

        Integer weight,
        Integer frameSize,
        Boolean discBrakes,

        Boolean isElectric,
        Integer batteryCapacty,
        Double autonomy,

        EBikeType bikeType
) implements VehicleCustomerResponseDto {
}
