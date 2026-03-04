package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import java.util.UUID;

public record MotorBikeCustomerResponseDto(
        UUID uuid,
        String brand,
        String model,
        String color,
        String requiredDrivingLicence,

        Integer weight,
        String transmission,
        Integer seatHeight,
        Integer power,
        Integer numberOfCylinders,
        Integer engineDisplacement,

        String motorBikeType
) implements VehicleCustomerResponseDto {
}
