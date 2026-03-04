package com.accenture.locationvoitures.service.dto.response;

public record CampingCarEquipmentResponseDto(
        Long id,
        Integer numberOfBerths,
        Boolean cookingEquipment,
        Boolean fridge,
        Boolean shower,
        Boolean bedLinen
) {
}
