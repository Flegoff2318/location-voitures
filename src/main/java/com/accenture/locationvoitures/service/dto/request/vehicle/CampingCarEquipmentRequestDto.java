package com.accenture.locationvoitures.service.dto.request.vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CampingCarEquipmentRequestDto(
        @NotNull(message = "equipment.numberofberths.null") @Min(value = 0,message = "equipment.numberofberths.invalid") Integer numberOfBerths,
        @NotNull(message = "equipment.cookingequipment.null") Boolean cookingEquipment,
        @NotNull(message = "equipment.fridge.null") Boolean fridge,
        @NotNull(message = "equipment.shower.null") Boolean shower,
        @NotNull(message = "equipment.bedlinen.null") Boolean bedLinen
) {
}
