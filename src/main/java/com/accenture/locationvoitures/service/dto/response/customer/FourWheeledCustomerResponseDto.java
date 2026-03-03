package com.accenture.locationvoitures.service.dto.response.customer;

public sealed interface FourWheeledCustomerResponseDto extends VehicleCustomerResponseDto permits CarCustomerResponseDto{
    Integer numberOfSeats();
    String fuelType();
    String transmission();
    Boolean airConditioning();
}
