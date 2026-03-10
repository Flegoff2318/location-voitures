package com.accenture.locationvoitures.service.dto.request.person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CustomerRequestDto(
        @NotNull(message = "person.firstname.null")@NotBlank(message = "person.firstname.blank") String firstname,
        @NotNull(message = "person.lastname.null")@NotBlank(message = "person.lastname.blank") String lastname,
        @Valid @NotNull(message = "customer.address.null") AddressRequestDto address,
        @NotNull(message = "person.email.null")@NotBlank(message = "person.email.blank") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "person.email.invalid") String email,
        @NotNull(message = "person.password.null")@NotBlank(message = "person.password.blank") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$",message = "person.password.invalid") String password,
        @NotNull(message = "customer.birthday.null")@NotBlank(message = "customer.birthday.blank") String birthday,
        List<String> drivingLicences
) {
}
