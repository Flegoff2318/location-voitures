package com.accenture.locationvoitures.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class FourWheeled extends Vehicle {
    private Integer numberOfSeats;
    private FuelType fuelType;
    private Transmission transmission;
    private Boolean airConditioning;
}
