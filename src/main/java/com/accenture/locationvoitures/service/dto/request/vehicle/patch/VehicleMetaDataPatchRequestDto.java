package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import jakarta.validation.constraints.Min;

public record VehicleMetaDataPatchRequestDto(
        @Min(value = 0, message = "metadata.dailyrentalprice.invalid")
        Double dailyRentalPrice,
        @Min(value = 0, message = "metadata.mileage.invalid")
        Integer mileage,
        Boolean active,
        Boolean outOfFleet
) {

}
