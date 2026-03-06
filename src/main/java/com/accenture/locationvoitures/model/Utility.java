package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.model.enumeration.EUtilityType;
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
public class Utility extends FourWheeled {
    private Double haulingCapacity;
    private Double ptac;
    private EUtilityType utilityType;

    @Override
    public void validate() {
        super.validate();
        if (this.getHaulingCapacity() == null)
            throw new VehicleException("Hauling capacity is null", HttpStatus.BAD_REQUEST);
        if (this.getHaulingCapacity() < 0)
            throw new VehicleException("Hauling capacity must be superior to 0", HttpStatus.BAD_REQUEST);
        if (this.getPtac() == null)
            throw new VehicleException("PTAC is null", HttpStatus.BAD_REQUEST);
        if (this.getPtac() < 0)
            throw new VehicleException("PTAC must be superior to 0", HttpStatus.BAD_REQUEST);
        if (this.getPtac() >7.5)
            throw new VehicleException("PTAC must be inferior to 7.5", HttpStatus.BAD_REQUEST);
        // MapStruct returns a null if the Enum Value is not found, so no additional check
        if (this.getUtilityType() == null)
            throw new VehicleException("Utility type is null", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void checkUpdateData() {
        super.checkUpdateData();
        if (this.getHaulingCapacity() != null && this.getHaulingCapacity() < 0)
            throw new VehicleException("Hauling capacity must be superior to 0", HttpStatus.BAD_REQUEST);
        if (this.getPtac() != null && this.getPtac() < 0)
            throw new VehicleException("PTAC must be superior to 0", HttpStatus.BAD_REQUEST);
    }


    public void applyPatch(Utility patchData) {
        super.applyPatch(patchData);
        if (patchData.getHaulingCapacity() != null)
            this.setHaulingCapacity(patchData.getHaulingCapacity());
        if (patchData.getPtac() != null)
            this.setPtac(patchData.getPtac());
        if (patchData.getUtilityType() != null)
            this.setUtilityType(patchData.getUtilityType());
    }

    public void evaluateRequiredDrivingLicence() {
        if(this.getPtac()<=3.5){
            this.setRequiredDrivingLicence(EDrivingLicence.B);
        }else if(this.getPtac()<=7.5){
            this.setRequiredDrivingLicence(EDrivingLicence.C1);
        }
    }
}
