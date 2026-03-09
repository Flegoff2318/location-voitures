package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import jakarta.validation.constraints.Min;

public record CampingCarEquipmentPatchRequestDto(

        @Min(value = 0,message = "equipment.numberofberths.invalid") Integer numberOfBerths,
        Boolean cookingEquipment,
        Boolean fridge,
        Boolean shower,
        Boolean bedLinen
) {
}
