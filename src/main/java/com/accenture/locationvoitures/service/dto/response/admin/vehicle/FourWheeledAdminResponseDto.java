package com.accenture.locationvoitures.service.dto.response.admin.vehicle;

import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;

public sealed interface FourWheeledAdminResponseDto extends VehicleAdminResponseDto permits CarAdminResponseDto,UtilityAdminResponseDto,CampingCarAdminResponseDto {
    Integer numberOfSeats();
    EFuelType fuelType();
    ETransmission transmission();
    Boolean airConditioning();
}
