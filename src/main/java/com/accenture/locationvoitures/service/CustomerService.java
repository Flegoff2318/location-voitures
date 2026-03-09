package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.person.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.patch.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.person.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponseDto add(CustomerRequestDto dto);

    CustomerResponseDto getCustomerDetailsByEmail(String email);

    void deleteCustomer(String email);

    CustomerResponseDto patch(String email, CustomerPatchRequestDto dto);
}
