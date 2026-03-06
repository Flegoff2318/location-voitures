package com.accenture.locationvoitures.service.dto.request.vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VehicleMetaDataRequestDto(
        @NotNull @Min(0) Double dailyRentalPrice,
        @NotNull @Min(0) Integer mileage,
        @NotNull Boolean active,
        @NotNull Boolean outOfFleet
) {
}
