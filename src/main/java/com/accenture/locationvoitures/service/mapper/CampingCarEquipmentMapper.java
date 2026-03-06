package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.CampingCarEquipment;
import com.accenture.locationvoitures.service.dto.request.vehicle.CampingCarEquipmentRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CampingCarEquipmentPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.CampingCarEquipmentResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CampingCarEquipmentMapper {
    CampingCarEquipment toEntity(CampingCarEquipmentRequestDto dto);

    CampingCarEquipment toEntity(CampingCarEquipmentPatchRequestDto dto);

    CampingCarEquipmentResponseDto toResponseDto(CampingCarEquipment campingCarEquipment);

}
