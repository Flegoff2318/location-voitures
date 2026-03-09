package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import com.accenture.locationvoitures.model.enumeration.ECampingCarType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
import com.accenture.locationvoitures.service.dto.response.CampingCarEquipmentResponseDto;

import java.util.UUID;

public record CampingCarCustomerResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        EDrivingLicence requiredDrivingLicence,

        Integer numberOfSeats,
        EFuelType fuelType,
        ETransmission transmission,
        Boolean airConditioning,

        Double ptac,
        Double height,
        CampingCarEquipmentResponseDto campingCarEquipment,

        ECampingCarType campingCarType
) implements FourWheeledCustomerResponseDto {
}
