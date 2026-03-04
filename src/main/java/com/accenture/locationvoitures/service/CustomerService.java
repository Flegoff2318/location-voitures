package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.person.patch.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.person.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponseDto add(CustomerRequestDto dto);

    List<CustomerResponseDto> customers();

    CustomerResponseDto getCustomerDetailsById(UUID uuid, PersonRequestDto credentials);

    CustomerResponseDto getCustomerDetailsByCredentials(PersonRequestDto credentials);

    void deleteCustomer(PersonRequestDto credentials);

    CustomerResponseDto patch(CustomerPatchRequestDto dto, PersonRequestDto credentials);
}
