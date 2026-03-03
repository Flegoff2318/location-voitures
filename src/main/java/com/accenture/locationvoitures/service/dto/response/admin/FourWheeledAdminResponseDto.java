package com.accenture.locationvoitures.service.dto.response.admin;

public sealed interface FourWheeledAdminResponseDto extends VehicleAdminResponseDto permits CarAdminResponseDto {
    Integer numberOfSeats();
    String fuelType();
    String transmission();
    Boolean airConditioning();
}
