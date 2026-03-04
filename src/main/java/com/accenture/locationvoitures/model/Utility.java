package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.model.enumeration.EUtilityType;
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
public class Utility extends FourWheeled {
    private Double haulingCapacity;
    private Integer ptac;
    private EUtilityType utilityType;
}
