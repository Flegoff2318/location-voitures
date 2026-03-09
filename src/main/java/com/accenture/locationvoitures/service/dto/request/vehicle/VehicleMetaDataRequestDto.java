package com.accenture.locationvoitures.service.dto.request.vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VehicleMetaDataRequestDto(
        @NotNull(message = "metadata.dailyrentalprice.null")
        @Min(value = 0,message = "metadata.dailyrentalprice.invalid")
        Double dailyRentalPrice,
        @NotNull(message = "metadata.mileage.null")
        @Min(value = 0, message = "metadata.mileage.invalid")
        Integer mileage,
        @NotNull(message = "metadata.active.null")
        Boolean active,
        @NotNull(message = "metadata.outoffleet.null")
        Boolean outOfFleet
) {
}
