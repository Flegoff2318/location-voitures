package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.CustomerException;
import com.accenture.locationvoitures.model.Customer;
import com.accenture.locationvoitures.repository.CustomerRepository;
import com.accenture.locationvoitures.service.CustomerService;
import com.accenture.locationvoitures.service.dto.request.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.CustomerResponseDto;
import com.accenture.locationvoitures.service.mapper.CustomerMapper;
import com.accenture.locationvoitures.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto dto) {
        Util.verifyCustomer(dto);

        Customer customer = customerMapper.toEntity(dto);
        customer.setRegistrationDate(LocalDate.now());
        Customer saved = customerRepository.save(customer);

        return customerMapper.toResponseDto(saved);
    }

    @Override
    public List<CustomerResponseDto> customers() {
        return List.of();
    }

    @Override
    public CustomerResponseDto getCustomerDetailsById(UUID uuid, PersonRequestDto dto) {
        Util.verifyPerson(dto);
        Optional<Customer> optCustomer = customerRepository.findById(uuid);
        if (optCustomer.isEmpty())
            throw new CustomerException("User not found", HttpStatus.NOT_FOUND);
        Customer customer = optCustomer.get();
        if (!customer.getEmail().equals(dto.email()) || !customer.getPassword().equals(dto.password()))
            throw new CustomerException("Access denied", HttpStatus.FORBIDDEN);
        return customerMapper.toResponseDto(customer);
    }

    @Override
    public CustomerResponseDto getCustomerDetailsByCredentials(PersonRequestDto dto) {
        Util.verifyPerson(dto);
        Customer customer = customerRepository.findByEmailAndPassword(dto.email(), dto.password());
        return customerMapper.toResponseDto(customer);
    }

    @Override
    public void deleteCustomer(PersonRequestDto dto) {
        Util.verifyPerson(dto);
        Optional<Customer> optCustomer = Optional.ofNullable(customerRepository.findByEmailAndPassword(dto.email(), dto.password()));
        if (optCustomer.isEmpty())
            throw new CustomerException("User not found", HttpStatus.NOT_FOUND);
        Customer customer = optCustomer.get();
        if (!customer.getEmail().equals(dto.email()) || !customer.getPassword().equals(dto.password()))
            throw new CustomerException("Access denied", HttpStatus.FORBIDDEN);
        customerRepository.delete(customer);
    }
}
