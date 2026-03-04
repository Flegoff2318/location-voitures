package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.model.enumeration.ECampingCarType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CampingCar extends FourWheeled {
    private Integer ptac;
    private Double height;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "camping_car_equipment_id")
    private CampingCarEquipment campingCarEquipment;
    private ECampingCarType campingCarType;
}
