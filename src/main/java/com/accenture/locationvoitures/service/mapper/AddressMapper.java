package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Address;
import com.accenture.locationvoitures.service.dto.request.person.AddressRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.AddressResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toEntity(AddressRequestDto dto);
    AddressResponseDto toResponseDto(Address entity);
}
