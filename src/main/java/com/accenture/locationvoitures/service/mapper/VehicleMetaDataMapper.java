package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.VehicleMetaData;
import com.accenture.locationvoitures.service.dto.request.VehicleMetaDataRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.VehicleMetaDataResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMetaDataMapper {
    VehicleMetaData toEntity(VehicleMetaDataRequestDto dto);

    VehicleMetaDataResponseDto toResponseDto(VehicleMetaData entity);
}
