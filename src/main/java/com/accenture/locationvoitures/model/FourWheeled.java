package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
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
    private EFuelType fuelType;
    private ETransmission transmission;
    private Boolean airConditioning;
}
