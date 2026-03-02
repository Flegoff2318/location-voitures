package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto dto);

    List<CustomerResponseDto> customers();

    CustomerResponseDto getCustomerDetailsById(UUID uuid, PersonRequestDto credentials);

    CustomerResponseDto getCustomerDetailsByCredentials(PersonRequestDto credentials);

    void deleteCustomer(PersonRequestDto credentials);

    CustomerResponseDto patch(CustomerPatchRequestDto dto, PersonRequestDto credentials);
}
