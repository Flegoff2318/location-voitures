package com.accenture.locationvoitures.service.dto.request.vehicle;

public record VehicleMetaDataRequestDto(
        Double dailyRentalPrice,
        Integer mileage,
        Boolean active,
        Boolean outOfFleet
) {
}
