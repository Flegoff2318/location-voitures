package com.accenture.locationvoitures.service.dto.response.customer;

import java.util.UUID;

public record CarCustomerResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        String requiredDrivingLicence,
        Integer numberOfSeats,
        String fuelType,
        String transmission,
        Boolean airConditioning,
        Integer numberOfLuggage,
        String carType
) implements FourWheeledCustomerResponseDto {
}
