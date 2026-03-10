package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends Person {
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private LocalDate birthday;
    private LocalDate registrationDate;
    private List<EDrivingLicence> drivingLicences;
    private Boolean active;
}
