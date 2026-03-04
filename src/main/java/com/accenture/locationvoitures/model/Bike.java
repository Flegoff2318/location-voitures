package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.model.enumeration.EBikeType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bike extends Vehicle {
    private Integer weight; // Kilograms
    private Integer frameSize; // Centimeters
    private Boolean discBrakes;

    private Boolean isElectric;
    private Integer batteryCapacity; // Watts
    private Double autonomy; // Hours, else Integer => minutes

    private EBikeType bikeType;
}
