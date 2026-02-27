package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.CustomerResponseDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto dto);
    List<CustomerResponseDto> customers();
    CustomerResponseDto getCustomerDetailsById(UUID uuid, PersonRequestDto dto);
    CustomerResponseDto getCustomerDetailsByCredentials(PersonRequestDto dto);
    void deleteCustomer(PersonRequestDto dto);
}
