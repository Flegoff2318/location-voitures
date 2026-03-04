package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Bike;
import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VehicleMetaDataMapper.class})
public interface BikeMapper {

    Bike toEntity(BikeRequestDto dto);
    Bike toEntity(BikePatchRequestDto dto);

    BikeAdminResponseDto toAdminResponseDto(Bike bike);
    BikeCustomerResponseDto toCustomerResponseDto(Bike bike);

}
