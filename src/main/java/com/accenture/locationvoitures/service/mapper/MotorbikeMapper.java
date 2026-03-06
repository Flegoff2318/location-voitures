package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Motorbike;
import com.accenture.locationvoitures.service.dto.request.vehicle.MotorbikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.MotorbikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.MotorbikeAdminResponseDto;
import com.accenture.locationvoitures.service.dto.response.customer.vehicle.MotorbikeCustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VehicleMetaDataMapper.class})
public interface MotorbikeMapper {
    Motorbike toEntity(MotorbikeRequestDto dto);
    Motorbike toEntity(MotorbikePatchRequestDto dto);

    MotorbikeAdminResponseDto toAdminResponseDto(Motorbike motorBike);
    MotorbikeCustomerResponseDto toCustomerResponseDto(Motorbike motorBike);
}
