package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

public sealed interface FourWheeledAdminResponseDto extends VehicleAdminResponseDto permits CarAdminResponseDto,UtilityAdminResponseDto,CampingCarAdminResponseDto {
    Integer numberOfSeats();
    String fuelType();
    String transmission();
    Boolean airConditioning();
}
