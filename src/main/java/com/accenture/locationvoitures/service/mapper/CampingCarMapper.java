package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.CampingCar;
import com.accenture.locationvoitures.service.dto.request.vehicle.CampingCarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CampingCarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CampingCarAdminResponseDto;
import com.accenture.locationvoitures.service.dto.response.customer.vehicle.CampingCarCustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VehicleMetaDataMapper.class, CampingCarEquipmentMapper.class})
public interface CampingCarMapper {
    CampingCar toEntity(CampingCarRequestDto dto);

    CampingCar toEntity(CampingCarPatchRequestDto dto);


    CampingCarAdminResponseDto toAdminResponseDto(CampingCar campingCar);

    CampingCarCustomerResponseDto toCustomerResponseDto(CampingCar campingCar);

}
