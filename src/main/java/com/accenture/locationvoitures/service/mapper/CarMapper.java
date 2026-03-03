package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Car;
import com.accenture.locationvoitures.service.dto.request.CarRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.CarAdminResponseDto;
import com.accenture.locationvoitures.service.dto.response.customer.CarCustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VehicleMetaDataMapper.class})
public interface CarMapper {

    Car toEntity(CarRequestDto dto);

    CarCustomerResponseDto toCustomerResponseDto(Car car);

    CarAdminResponseDto toAdminResponseDto(Car car);
}
