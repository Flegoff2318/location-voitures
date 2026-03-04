package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.model.enumeration.EMotorbikeType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
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
public class Motorbike extends Vehicle {
    private Integer weight;
    private ETransmission transmission;
    private Integer seatHeight; // Centimeters
    private Integer power;
    private Integer numberOfCylinders;
    private Integer engineDisplacement; // CC
    private EMotorbikeType motorBikeType;
}
