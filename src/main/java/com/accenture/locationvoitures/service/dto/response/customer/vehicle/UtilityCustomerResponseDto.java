package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import java.util.UUID;

public record UtilityCustomerResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        String requiredDrivingLicence,

        Integer numberOfSeats,
        String fuelType,
        String transmission,
        Boolean airConditioning,

        Double haulingCapacity,
        Integer ptac,

        String utilityType
) implements FourWheeledCustomerResponseDto {
}
