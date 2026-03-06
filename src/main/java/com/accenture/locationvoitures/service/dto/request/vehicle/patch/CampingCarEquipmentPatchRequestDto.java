package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import jakarta.validation.constraints.Min;

public record CampingCarEquipmentPatchRequestDto(

        @Min(1) Integer numberOfBerths,
        Boolean cookingEquipment,
        Boolean fridge,
        Boolean shower,
        Boolean bedLinen
) {
}
