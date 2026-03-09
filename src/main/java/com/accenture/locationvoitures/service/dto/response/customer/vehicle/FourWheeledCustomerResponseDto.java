package com.accenture.locationvoitures.service.dto.response.customer.vehicle;

import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;

public sealed interface FourWheeledCustomerResponseDto extends VehicleCustomerResponseDto permits CarCustomerResponseDto, CampingCarCustomerResponseDto, UtilityCustomerResponseDto {
    Integer numberOfSeats();

    EFuelType fuelType();

    ETransmission transmission();

    Boolean airConditioning();
}
