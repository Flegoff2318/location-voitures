package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AddressException;
import com.accenture.locationvoitures.exception.CustomerException;
import com.accenture.locationvoitures.model.Address;
import com.accenture.locationvoitures.model.Customer;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.repository.CustomerRepository;
import com.accenture.locationvoitures.service.CustomerService;
import com.accenture.locationvoitures.service.dto.request.person.AddressRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.patch.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.person.CustomerResponseDto;
import com.accenture.locationvoitures.service.mapper.CustomerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMER = "ROLE_CUSTOMER";
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponseDto add(CustomerRequestDto dto) {
        verifyCustomer(dto);

        Customer customer = customerMapper.toEntity(dto);
        if(customerRepository.findByEmail(customer.getEmail()).isPresent())
            throw new CustomerException("email.used",HttpStatus.BAD_REQUEST);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(CUSTOMER);
        customer.setRegistrationDate(LocalDate.now());
        customer.setActive(true);
        Customer saved = customerRepository.save(customer);

        return customerMapper.toResponseDto(saved);
    }

    @Override
    public CustomerResponseDto getCustomerDetailsByEmail(String email) {
        Optional<Customer> optCustomer = customerRepository.findByEmail(email);
        if (optCustomer.isEmpty())
            throw new EntityNotFoundException("customer.notfound");
        Customer customer = optCustomer.get();
        return customerMapper.toResponseDto(customer);
    }


    @Override
    public void deleteCustomer(String email) {
        Optional<Customer> optCustomer = customerRepository.findByEmail(email);
        if (optCustomer.isEmpty())
            throw new EntityNotFoundException("customer.notfound");
        Customer customer = optCustomer.get();
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponseDto patch(String email, CustomerPatchRequestDto dto) {
        Optional<Customer> optCustomer = customerRepository.findByEmail(email);
        if (optCustomer.isEmpty())
            throw new CustomerException("customer.notfound", HttpStatus.NOT_FOUND);
        Customer customer = optCustomer.get();

        Customer patched = patchCustomerData(dto, customer);
        return customerMapper.toResponseDto(patched);
    }

    private @NonNull Customer patchCustomerData(CustomerPatchRequestDto dto, Customer customer) {
        Customer customerPatchData = customerMapper.toEntity(dto);
        if (dto.birthday() != null) {
            if (dto.birthday().isBlank())
                throw new CustomerException("customer.birthday.blank", HttpStatus.BAD_REQUEST);
            customer.setBirthday(customerPatchData.getBirthday());

        }
        if (dto.password() != null) {
            if (dto.password().isBlank())
                throw new CustomerException("person.password.blank", HttpStatus.BAD_REQUEST);
            customer.setPassword(passwordEncoder.encode(customerPatchData.getPassword()));

        }
        if (dto.lastname() != null) {
            if (dto.lastname().isBlank())
                throw new CustomerException("person.lastname.blank", HttpStatus.BAD_REQUEST);
            customer.setLastname(customerPatchData.getLastname());

        }
        if (dto.firstname() != null) {
            if (dto.firstname().isBlank())
                throw new CustomerException("person.firstname.blank", HttpStatus.BAD_REQUEST);
            customer.setFirstname(customerPatchData.getFirstname());
        }


        patchCustomerDrivingLicences(dto, customer, customerPatchData);
        if (dto.address() != null) {
            Address customerAddress = patchAddressData(dto, customer, customerPatchData);
            customer.setAddress(customerAddress);
        }
        return customerRepository.save(customer);
    }

    private void patchCustomerDrivingLicences(CustomerPatchRequestDto dto, Customer customer, Customer customerPatchData) {
        if (dto.drivingLicences() != null) {
            if (!dto.drivingLicences().isEmpty()) {
                dto.drivingLicences().forEach(s -> {
                    try {
                        EDrivingLicence.valueOf(s);
                    } catch (IllegalArgumentException _) {
                        throw new CustomerException("customer.licence.invalid", HttpStatus.BAD_REQUEST);
                    }
                });
            }
            customer.setDrivingLicences(customerPatchData.getDrivingLicences());
        }
    }

    private Address patchAddressData(CustomerPatchRequestDto dto, Customer customer, Customer customerPatchData) {
        Address customerAddress = customer.getAddress();
        if (dto.address().street() != null) {
            if (dto.address().street().isBlank())
                throw new AddressException("address.street.blank", HttpStatus.BAD_REQUEST);
            customerAddress.setStreet(customerPatchData.getAddress().getStreet());
        }

        if (dto.address().postalCode() != null) {
            if (dto.address().postalCode().isBlank())
                throw new AddressException("address.postalcode.blank", HttpStatus.BAD_REQUEST);
            customerAddress.setPostalCode(customerPatchData.getAddress().getPostalCode());
        }
        if (dto.address().city() != null) {
            if (dto.address().city().isBlank())
                throw new AddressException("address.city.blank", HttpStatus.BAD_REQUEST);
            customerAddress.setCity(customerPatchData.getAddress().getCity());
        }
        return customerAddress;
    }

    private void verifyCustomer(CustomerRequestDto dto) {
        if (dto == null)
            throw new CustomerException("dto.null", HttpStatus.BAD_REQUEST);
        verifyAddress(dto.address());
        if (dto.firstname() == null)
            throw new CustomerException("person.firstname.null", HttpStatus.BAD_REQUEST);
        if(dto.firstname().isBlank())
            throw new CustomerException("person.firstname.blank", HttpStatus.BAD_REQUEST);
        if (dto.lastname() == null)
            throw new CustomerException("person.lastname.null", HttpStatus.BAD_REQUEST);
        if(dto.lastname().isBlank())
            throw new CustomerException("person.lastname.blank", HttpStatus.BAD_REQUEST);
        if (dto.email() == null)
            throw new CustomerException("person.email.null", HttpStatus.BAD_REQUEST);
        if(dto.email().isBlank())
            throw new CustomerException("person.email.blank", HttpStatus.BAD_REQUEST);
        if (dto.password() == null)
            throw new CustomerException("person.password.null", HttpStatus.BAD_REQUEST);
        if(dto.password().isBlank())
            throw new CustomerException("person.password.blank", HttpStatus.BAD_REQUEST);
        if (dto.birthday() == null)
            throw new CustomerException("customer.birthday.null", HttpStatus.BAD_REQUEST);
        if(dto.birthday().isBlank())
            throw new CustomerException("customer.birthday.blank", HttpStatus.BAD_REQUEST);
    }

    private void verifyAddress(AddressRequestDto dto) {
        if (dto == null)
            throw new AddressException("dto.null", HttpStatus.BAD_REQUEST);
        if (dto.street() == null)
            throw new AddressException("address.street.null", HttpStatus.BAD_REQUEST);
        if(dto.street().isBlank())
            throw new AddressException("address.street.blank", HttpStatus.BAD_REQUEST);
        if (dto.postalCode() == null)
            throw new AddressException("address.postalcode.null", HttpStatus.BAD_REQUEST);
        if(dto.postalCode().isBlank())
            throw new AddressException("address.postalcode.blank", HttpStatus.BAD_REQUEST);
        if (dto.city() == null)
            throw new AddressException("address.city.null", HttpStatus.BAD_REQUEST);
        if(dto.city().isBlank())
            throw new AddressException("address.city.blank", HttpStatus.BAD_REQUEST);
    }
}
