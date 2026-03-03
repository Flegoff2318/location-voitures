package com.accenture.locationvoitures.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID uuid;
    protected String brand;
    protected String model;
    protected String color;
    protected DrivingLicence requiredDrivingLicence;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_meta_data_id")
    protected VehicleMetaData vehicleMetaData;
}
