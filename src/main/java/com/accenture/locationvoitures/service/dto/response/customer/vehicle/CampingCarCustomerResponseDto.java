package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import com.accenture.locationvoitures.service.dto.response.CampingCarEquipmentResponseDto;

import java.util.UUID;

public record CampingCarCustomerResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        String requiredDrivingLicence,

        Integer numberOfSeats,
        String fuelType,
        String transmission,
        Boolean airConditioning,

        Integer ptac,
        Double height,
        CampingCarEquipmentResponseDto campingCarEquipment,

        String campingCarType
) implements FourWheeledCustomerResponseDto {
}
