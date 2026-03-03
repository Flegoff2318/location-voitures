package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AddressException;
import com.accenture.locationvoitures.exception.CustomerException;
import com.accenture.locationvoitures.model.Address;
import com.accenture.locationvoitures.model.Customer;
import com.accenture.locationvoitures.model.DrivingLicence;
import com.accenture.locationvoitures.repository.CustomerRepository;
import com.accenture.locationvoitures.service.CustomerService;
import com.accenture.locationvoitures.service.dto.request.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.CustomerResponseDto;
import com.accenture.locationvoitures.service.mapper.CustomerMapper;
import com.accenture.locationvoitures.service.util.Util;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
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
    public CustomerResponseDto getCustomerDetailsById(UUID uuid, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Optional<Customer> optCustomer = customerRepository.findById(uuid);
        if (optCustomer.isEmpty())
            throw new CustomerException("User not found", HttpStatus.NOT_FOUND);
        Customer customer = optCustomer.get();
        if (!customer.getEmail().equals(credentials.email()) || !customer.getPassword().equals(credentials.password()))
            throw new CustomerException("Access denied", HttpStatus.FORBIDDEN);
        return customerMapper.toResponseDto(customer);
    }

    @Override
    public CustomerResponseDto getCustomerDetailsByCredentials(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Customer customer = customerRepository.findByEmailAndPassword(credentials.email(), credentials.password());
        return customerMapper.toResponseDto(customer);
    }

    @Override
    public void deleteCustomer(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Optional<Customer> optCustomer = Optional.ofNullable(customerRepository.findByEmailAndPassword(credentials.email(), credentials.password()));
        if (optCustomer.isEmpty())
            throw new CustomerException("User not found", HttpStatus.NOT_FOUND);
        Customer customer = optCustomer.get();
        if (!customer.getEmail().equals(credentials.email()) || !customer.getPassword().equals(credentials.password()))
            throw new CustomerException("Access denied", HttpStatus.FORBIDDEN);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponseDto patch(CustomerPatchRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Customer customer = getCustomer(dto, credentials);

        Customer patched = patchCustomerData(dto, customer);
        return customerMapper.toResponseDto(patched);
    }

    private @NonNull Customer patchCustomerData(CustomerPatchRequestDto dto, Customer customer) {
        Customer customerPatchData = customerMapper.toEntity(dto);
        if (dto.birthday() != null) {
            if (dto.birthday().isBlank())
                throw new CustomerException("Birthday can't be blank", HttpStatus.BAD_REQUEST);
            customer.setBirthday(customerPatchData.getBirthday());

        }
        if (dto.password() != null) {
            if (dto.password().isBlank())
                throw new CustomerException("Password can't be blank", HttpStatus.BAD_REQUEST);
            customer.setPassword(customerPatchData.getPassword());

        }
        if (dto.lastname() != null) {
            if (dto.lastname().isBlank())
                throw new CustomerException("Lastname can't be blank", HttpStatus.BAD_REQUEST);
            customer.setLastname(customerPatchData.getLastname());

        }
        if (dto.firstname() != null) {
            if (dto.firstname().isBlank())
                throw new CustomerException("Firstname can't be blank", HttpStatus.BAD_REQUEST);
            customer.setFirstname(customerPatchData.getFirstname());
        }


        patchCustomerDrivingLicences(dto, customer, customerPatchData);
        if (dto.address() != null) {
            Address customerAddress = patchAddressData(dto, customer, customerPatchData);
            customer.setAddress(customerAddress);
        }
        return customerRepository.save(customer);
    }

    private static void patchCustomerDrivingLicences(CustomerPatchRequestDto dto, Customer customer, Customer customerPatchData) {
        if(dto.drivingLicences()!=null){
            if (!dto.drivingLicences().isEmpty()) {
                dto.drivingLicences().forEach(s -> {
                    try {
                        DrivingLicence.valueOf(s);
                    } catch (IllegalArgumentException _) {
                        throw new CustomerException("Unknown Licence", HttpStatus.BAD_REQUEST);
                    }
                });
            }
            customer.setDrivingLicences(customerPatchData.getDrivingLicences());
        }
    }

    private static Address patchAddressData(CustomerPatchRequestDto dto, Customer customer, Customer customerPatchData) {
        Address customerAddress = customer.getAddress();
        if (dto.address().street() != null) {
            if (dto.address().street().isBlank())
                throw new AddressException("Street can't be blank", HttpStatus.BAD_REQUEST);
            customerAddress.setStreet(customerPatchData.getAddress().getStreet());
        }

        if (dto.address().postalCode() != null) {
            if (dto.address().postalCode().isBlank())
                throw new AddressException("PostalCode can't be blank", HttpStatus.BAD_REQUEST);
            customerAddress.setPostalCode(customerPatchData.getAddress().getPostalCode());
        }
        if (dto.address().city() != null) {
            if (dto.address().city().isBlank())
                throw new AddressException("City can't be blank", HttpStatus.BAD_REQUEST);
            customerAddress.setCity(customerPatchData.getAddress().getCity());
        }
        return customerAddress;
    }

    private @NonNull Customer getCustomer(CustomerPatchRequestDto dto, PersonRequestDto credentials) {
        if (dto == null)
            throw new CustomerException("DTO is null", HttpStatus.BAD_REQUEST);
        Optional<Customer> optCustomer = Optional.ofNullable(customerRepository.findByEmailAndPassword(credentials.email(), credentials.password()));
        if (optCustomer.isEmpty())
            throw new CustomerException("User not found", HttpStatus.NOT_FOUND);
        Customer customer = optCustomer.get();
        if (!customer.getEmail().equals(credentials.email()) || !customer.getPassword().equals(credentials.password()))
            throw new CustomerException("Access denied", HttpStatus.FORBIDDEN);
        return customer;
    }
}
