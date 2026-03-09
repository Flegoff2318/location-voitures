package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.model.enumeration.EMotorbikeType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;

import java.util.UUID;

public record MotorbikeCustomerResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        EDrivingLicence requiredDrivingLicence,

        Integer weight,
        ETransmission transmission,
        Integer seatHeight,
        Integer power,
        Integer numberOfCylinders,
        Integer engineDisplacement,

        EMotorbikeType motorBikeType
) implements VehicleCustomerResponseDto {
}
