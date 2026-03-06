package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import java.util.UUID;

public sealed interface VehicleCustomerResponseDto permits FourWheeledCustomerResponseDto, BikeCustomerResponseDto, MotorbikeCustomerResponseDto {
    UUID uuid();
    String brand();
    String model();
    String color();
    String requiredDrivingLicence();
}
