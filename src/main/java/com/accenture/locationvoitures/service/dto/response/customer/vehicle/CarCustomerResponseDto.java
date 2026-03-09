package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import com.accenture.locationvoitures.model.enumeration.ECarType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;

import java.util.UUID;

public record CarCustomerResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        EDrivingLicence requiredDrivingLicence,
        Integer numberOfSeats,
        EFuelType fuelType,
        ETransmission transmission,
        Boolean airConditioning,
        Integer numberOfLuggage,
        ECarType carType
) implements FourWheeledCustomerResponseDto {
}
