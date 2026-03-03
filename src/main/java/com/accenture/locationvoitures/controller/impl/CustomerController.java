package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.CustomerApi;
import com.accenture.locationvoitures.service.CustomerService;
import com.accenture.locationvoitures.service.dto.request.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@AllArgsConstructor
@RestController
public class CustomerController implements CustomerApi {
    private CustomerService customerService;

    @Override
    public ResponseEntity<Void> create(@Valid CustomerRequestDto dto) {
        CustomerResponseDto responseDto = customerService.addCustomer(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @Override
    public ResponseEntity<CustomerResponseDto> readAccountDetails(String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(customerService.getCustomerDetailsByCredentials(credentials));
    }

    @Override
    public ResponseEntity<CustomerResponseDto> delete(String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        customerService.deleteCustomer(credentials);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CustomerResponseDto> patch(String base64Header, CustomerPatchRequestDto dto) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(customerService.patch(dto,credentials));
    }

    private @NonNull PersonRequestDto getCredentials(String base64Header) {
        byte[] decoded = Base64.getDecoder().decode(base64Header.split(" ")[1]);
        String[] content = new String(decoded, StandardCharsets.UTF_8).split(":");
        return new PersonRequestDto(content[0], content[1]);
    }

}
