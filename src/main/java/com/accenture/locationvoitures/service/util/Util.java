package com.accenture.locationvoitures.service.util;

import com.accenture.locationvoitures.exception.AddressException;
import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.exception.CustomerException;
import com.accenture.locationvoitures.exception.PersonException;
import com.accenture.locationvoitures.service.dto.request.person.AddressRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import org.springframework.http.HttpStatus;

public class Util {

    private Util() {
    }

    public static void verifyCustomer(CustomerRequestDto dto) {
        if (dto == null)
            throw new CustomerException("DTO is null", HttpStatus.BAD_REQUEST);
        verifyAddress(dto.address());
        if (dto.firstname() == null || dto.firstname().isBlank())
            throw new CustomerException("Firstname is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.lastname() == null || dto.lastname().isBlank())
            throw new CustomerException("Lastname is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.email() == null || dto.email().isBlank())
            throw new CustomerException("Email is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.password() == null || dto.password().isBlank())
            throw new CustomerException("Password is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.birthday() == null || dto.birthday().isBlank())
            throw new CustomerException("Birthday is null or blank", HttpStatus.BAD_REQUEST);
    }

    public static void verifyAdmin(AdminRequestDto dto) {
        if (dto == null)
            throw new AdminException("DTO is null", HttpStatus.BAD_REQUEST);
        if (dto.firstname() == null || dto.firstname().isBlank())
            throw new AdminException("Firstname is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.lastname() == null || dto.lastname().isBlank())
            throw new AdminException("Lastname is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.email() == null || dto.email().isBlank())
            throw new AdminException("Email is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.password() == null || dto.password().isBlank())
            throw new AdminException("Password is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.companyFunction() == null || dto.companyFunction().isBlank())
            throw new AdminException("CompanyFunction is null or blank", HttpStatus.BAD_REQUEST);
    }

    public static void verifyPerson(PersonRequestDto dto) {
        if (dto == null)
            throw new PersonException("DTO is null", HttpStatus.BAD_REQUEST);
        if (dto.email() == null || dto.email().isBlank())
            throw new PersonException("Email is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.password() == null || dto.password().isBlank())
            throw new PersonException("Password is null or blank", HttpStatus.BAD_REQUEST);
    }

    public static void verifyAddress(AddressRequestDto dto) {
        if (dto == null)
            throw new AddressException("DTO is null", HttpStatus.BAD_REQUEST);
        if (dto.street() == null || dto.street().isBlank())
            throw new AddressException("Street is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.postalCode() == null || dto.postalCode().isBlank())
            throw new AddressException("Postal Code is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.city() == null || dto.city().isBlank())
            throw new AddressException("City is null or blank", HttpStatus.BAD_REQUEST);
    }
}
