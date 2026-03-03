package com.accenture.locationvoitures.service.dto.response.admin;

public record VehicleMetaDataResponseDto(
        Double dailyRentalPrice,
        Integer mileage,
        Boolean active,
        Boolean outOfFleet
) {
}
