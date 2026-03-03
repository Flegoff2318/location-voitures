package com.accenture.locationvoitures.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VehicleMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double dailyRentalPrice;
    private Integer mileage;
    private Boolean active;
    private Boolean outOfFleet;

    public VehicleMetaData(Double dailyRentalPrice, Integer mileage, Boolean active, Boolean outOfFleet) {
        this.dailyRentalPrice = dailyRentalPrice;
        this.mileage = mileage;
        this.active = active;
        this.outOfFleet = outOfFleet;
    }
}
