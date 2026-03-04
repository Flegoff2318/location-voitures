package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

public sealed interface FourWheeledCustomerResponseDto extends VehicleCustomerResponseDto permits CarCustomerResponseDto, CampingCarCustomerResponseDto, UtilityCustomerResponseDto {
    Integer numberOfSeats();

    String fuelType();

    String transmission();

    Boolean airConditioning();
}
