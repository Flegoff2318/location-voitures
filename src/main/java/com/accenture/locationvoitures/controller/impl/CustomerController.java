package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.CustomerApi;
import com.accenture.locationvoitures.service.CustomerService;
import com.accenture.locationvoitures.service.dto.request.person.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.patch.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.person.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
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
        CustomerResponseDto responseDto = customerService.add(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(responseDto.email())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<CustomerResponseDto> readAccountDetails(@RequestHeader(name = "authorization") String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(customerService.getCustomerDetailsByEmail(credentials.email()));
    }

    @Override
    public ResponseEntity<CustomerResponseDto> delete(@RequestHeader(name = "authorization") String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        customerService.deleteCustomer(credentials.email());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CustomerResponseDto> patch(@RequestHeader(name = "authorization") String base64Header, CustomerPatchRequestDto dto) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(customerService.patch(credentials.email(), dto));
    }

    private @NonNull PersonRequestDto getCredentials(String base64Header) {
        byte[] decoded = Base64.getDecoder().decode(base64Header.split(" ")[1]);
        String[] content = new String(decoded, StandardCharsets.UTF_8).split(":");
        return new PersonRequestDto(content[0], content[1]);
    }

}
