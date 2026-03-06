package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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
    protected EDrivingLicence requiredDrivingLicence;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_meta_data_id")
    protected VehicleMetaData vehicleMetaData;

    public void validate() {
        if (this.getBrand().isBlank())
            throw new VehicleException("Brand is blank", HttpStatus.BAD_REQUEST);
        if (this.getModel().isBlank())
            throw new VehicleException("Model is blank", HttpStatus.BAD_REQUEST);
        if (this.getColor().isBlank())
            throw new VehicleException("Color is blank", HttpStatus.BAD_REQUEST);
        if (this.getVehicleMetaData() == null)
            throw new VehicleException("VehicleMetaData is null", HttpStatus.BAD_REQUEST);
        vehicleMetaData.validate();
    }

    public void checkUpdateData() {
        if (this.getBrand() != null && this.getBrand().isBlank())
            throw new VehicleException("Brand is blank", HttpStatus.BAD_REQUEST);
        if (this.getModel() != null && this.getModel().isBlank())
            throw new VehicleException("Model is blank", HttpStatus.BAD_REQUEST);
        if (this.getColor() != null && this.getColor().isBlank())
            throw new VehicleException("Color is blank", HttpStatus.BAD_REQUEST);
        if (this.getVehicleMetaData() != null)
            vehicleMetaData.checkUpdateData();
    }

    public void applyPatch(Vehicle patchData) {
        if(patchData.getBrand()!=null)
            this.setBrand(patchData.getBrand());
        if(patchData.getModel()!=null)
            this.setModel(patchData.getModel());
        if(patchData.getColor()!=null)
            this.setColor(patchData.getColor());
        if(patchData.getVehicleMetaData()!=null)
            this.getVehicleMetaData().applyPatch(patchData.getVehicleMetaData());
    }
}
