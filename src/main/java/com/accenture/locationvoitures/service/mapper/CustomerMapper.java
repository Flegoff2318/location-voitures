package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Customer;
import com.accenture.locationvoitures.service.dto.request.person.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.patch.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.person.CustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface CustomerMapper {

    Customer toEntity(CustomerRequestDto dto);
    Customer toEntity(CustomerPatchRequestDto dto);

    CustomerResponseDto toResponseDto(Customer entity);
}
