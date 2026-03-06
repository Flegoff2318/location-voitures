package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.model.enumeration.EBikeType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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

    @Override
    public void validate() {
        super.validate();
        if (this.getWeight() == null)
            throw new VehicleException("Weight is null", HttpStatus.BAD_REQUEST);
        if (this.getWeight() <= 0)
            throw new VehicleException("Weight must be superior to 0", HttpStatus.BAD_REQUEST);
        if (this.getFrameSize() == null)
            throw new VehicleException("Frame size is null", HttpStatus.BAD_REQUEST);
        if (this.getFrameSize() <= 0)
            throw new VehicleException("Frame size must be superior to 0", HttpStatus.BAD_REQUEST);
        if (this.getDiscBrakes() == null)
            throw new VehicleException("Disc brakes is null", HttpStatus.BAD_REQUEST);
        if (this.getIsElectric() == null)
            throw new VehicleException("Is electric is null", HttpStatus.BAD_REQUEST);
        if (this.getIsElectric()) {
            if (this.getBatteryCapacity() == null)
                throw new VehicleException("Battery capacity is null", HttpStatus.BAD_REQUEST);
            if (this.getAutonomy() == null)
                throw new VehicleException("Autonomy is null", HttpStatus.BAD_REQUEST);
            if (this.getBatteryCapacity() <= 0)
                throw new VehicleException("Battery capacity must be superior to 0", HttpStatus.BAD_REQUEST);
            if (this.getAutonomy() <= 0)
                throw new VehicleException("Battery capacity must be superior to 0", HttpStatus.BAD_REQUEST);
        }
        if (this.getBikeType() == null)
            throw new VehicleException("Bike type is null", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void checkUpdateData() {
        super.checkUpdateData();
        if (this.getWeight() != null && this.getWeight() <= 0)
            throw new VehicleException("Weight must be superior to 0", HttpStatus.BAD_REQUEST);
        if (this.getFrameSize() != null && this.getFrameSize() <= 0)
            throw new VehicleException("Frame size must be superior to 0", HttpStatus.BAD_REQUEST);

        if (this.getIsElectric()) {
            if (this.getBatteryCapacity() != null && this.getBatteryCapacity() <= 0)
                throw new VehicleException("Battery capacity must be superior to 0", HttpStatus.BAD_REQUEST);
            if (this.getAutonomy() != null && this.getAutonomy() <= 0)
                throw new VehicleException("Autonomy must be superior to 0", HttpStatus.BAD_REQUEST);
        }
    }


    public void applyPatch(Bike patchData) {
        super.applyPatch(patchData);
        if (patchData.getWeight() != null)
            this.setWeight(patchData.getWeight());
        if (patchData.getFrameSize() != null)
            this.setFrameSize(patchData.getFrameSize());
        if (patchData.getDiscBrakes() != null)
            this.setDiscBrakes(patchData.getDiscBrakes());
        if (patchData.getIsElectric() != null && patchData.getIsElectric()) {
            if (!this.getIsElectric()) {
                if (patchData.getBatteryCapacity() == null)
                    throw new VehicleException("Battery capacity is null", HttpStatus.BAD_REQUEST);
                if (patchData.getAutonomy() == null)
                    throw new VehicleException("Autonomy is null", HttpStatus.BAD_REQUEST);
            }
            if (patchData.getBatteryCapacity() != null)
                this.setBatteryCapacity(patchData.getBatteryCapacity());
            if (patchData.getAutonomy() != null && patchData.getAutonomy() <= 0)
                this.setAutonomy(patchData.getAutonomy());
        }
        if (patchData.getBikeType() != null)
            this.setBikeType(patchData.getBikeType());
    }
}
