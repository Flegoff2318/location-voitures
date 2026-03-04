package com.accenture.locationvoitures.service.dto.request.vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CampingCarEquipmentRequestDto(
        @NotNull @Min(1) Integer numberOfBerths,
        @NotNull Boolean cookingEquipment,
        @NotNull Boolean fridge,
        @NotNull Boolean shower,
        @NotNull Boolean bedLinen
) {
}
