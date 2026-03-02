package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Address;
import com.accenture.locationvoitures.model.Customer;
import com.accenture.locationvoitures.service.dto.request.AddressRequestDto;
import com.accenture.locationvoitures.service.dto.request.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.response.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequestDto dto);
    Customer toEntity(CustomerPatchRequestDto dto);

    @Mapping(source = "address.city", target ="city")
    @Mapping(source = "address.postalCode", target ="postalCode")
    @Mapping(source = "address.street", target ="street")
    CustomerResponseDto toResponseDto(Customer entity);

    Address toAddressEntity(AddressRequestDto dto);
}
