package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Car;
import com.accenture.locationvoitures.service.dto.request.vehicle.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CarAdminResponseDto;
import com.accenture.locationvoitures.service.dto.response.customer.vehicle.CarCustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VehicleMetaDataMapper.class})
public interface CarMapper {

    Car toEntity(CarRequestDto dto);
    Car toEntity(CarPatchRequestDto dto);

    CarCustomerResponseDto toCustomerResponseDto(Car car);

    CarAdminResponseDto toAdminResponseDto(Car car);

}
