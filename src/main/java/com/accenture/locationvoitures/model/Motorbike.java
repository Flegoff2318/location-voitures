package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.model.enumeration.EMotorbikeType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
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
public class Motorbike extends Vehicle {
    private Integer weight;
    private ETransmission transmission;
    private Integer seatHeight; // Centimeters
    private Integer power;
    private Integer numberOfCylinders;
    private Integer engineDisplacement; // CC
    private EMotorbikeType motorBikeType;

    @Override
    public void validate() {
        super.validate();
        if (this.getWeight() == null)
            throw new VehicleException("motorbike.weight.null", HttpStatus.BAD_REQUEST);
        if (this.getWeight() < 0)
            throw new VehicleException("motorbike.weight.invalid", HttpStatus.BAD_REQUEST);
        // MapStruct returns a null if the Enum Value is not found, so no additional check
        if (this.getTransmission() == null)
            throw new VehicleException("transmission.null", HttpStatus.BAD_REQUEST);
        if (this.getSeatHeight() == null)
            throw new VehicleException("motorbike.seatheight.null", HttpStatus.BAD_REQUEST);
        if (this.getSeatHeight() < 0)
            throw new VehicleException("motorbike.seatheight.invalid", HttpStatus.BAD_REQUEST);
        if (this.getPower() == null)
            throw new VehicleException("motorbike.power.null", HttpStatus.BAD_REQUEST);
        if (this.getPower() < 0)
            throw new VehicleException("motorbike.power.invalid", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfCylinders() == null)
            throw new VehicleException("motorbike.cylinders.null", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfCylinders() < 0)
            throw new VehicleException("motorbike.cylinders.invalid", HttpStatus.BAD_REQUEST);
        if (this.getEngineDisplacement() == null)
            throw new VehicleException("motorbike.enginedisplacement.null", HttpStatus.BAD_REQUEST);
        if (this.getEngineDisplacement() < 0)
            throw new VehicleException("motorbike.enginedisplacement.invalid", HttpStatus.BAD_REQUEST);
        // MapStruct returns a null if the Enum Value is not found, so no additional check
        if (this.getMotorBikeType() == null)
            throw new VehicleException("motorbike.type.null", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void checkUpdateData() {
        super.checkUpdateData();
        if (this.getWeight() != null && this.getWeight() < 0)
            throw new VehicleException("motorbike.weight.invalid", HttpStatus.BAD_REQUEST);
        if (this.getSeatHeight() != null && this.getSeatHeight() < 0)
            throw new VehicleException("motorbike.seatheight.invalid", HttpStatus.BAD_REQUEST);
        if (this.getPower() != null && this.getPower() < 0)
            throw new VehicleException("motorbike.power.invalid", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfCylinders() != null && this.getNumberOfCylinders() < 0)
            throw new VehicleException("motorbike.cylinders.invalid", HttpStatus.BAD_REQUEST);
        if (this.getEngineDisplacement() != null && this.getEngineDisplacement() < 0)
            throw new VehicleException("motorbike.enginedisplacement.invalid", HttpStatus.BAD_REQUEST);
    }

    public void applyPatch(Motorbike patchData) {
        super.applyPatch(patchData);
        if (patchData.getWeight() != null)
            this.setWeight(patchData.getWeight());
        if (patchData.getTransmission() != null)
            this.setTransmission(patchData.getTransmission());
        if (patchData.getSeatHeight() != null)
            this.setSeatHeight(patchData.getSeatHeight());
        if (patchData.getPower() != null)
            this.setPower(patchData.getPower());
        if (patchData.getNumberOfCylinders() != null)
            this.setNumberOfCylinders(patchData.getNumberOfCylinders());
        if (patchData.getEngineDisplacement() != null)
            this.setEngineDisplacement(patchData.getEngineDisplacement());
        if (patchData.getMotorBikeType() != null)
            this.setMotorBikeType(patchData.getMotorBikeType());
    }

    public void evaluateRequiredDrivingLicence() {
        if(this.getEngineDisplacement()<=125 && this.getPower()<=11){
            this.setRequiredDrivingLicence(EDrivingLicence.A1);
        } else if (this.getPower()<=35) {
            this.setRequiredDrivingLicence(EDrivingLicence.A2);
        } else {
            this.setRequiredDrivingLicence(EDrivingLicence.A);
        }
    }
}
