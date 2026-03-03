package com.accenture.locationvoitures.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends Person {
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private LocalDate birthday;
    private LocalDate registrationDate;
    private List<DrivingLicence> drivingLicences;
    private boolean active;
}
