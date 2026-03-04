package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

public record VehicleMetaDataPatchRequestDto(
        Double dailyRentalPrice,
        Integer mileage,
        Boolean active,
        Boolean outOfFleet
) {

}
