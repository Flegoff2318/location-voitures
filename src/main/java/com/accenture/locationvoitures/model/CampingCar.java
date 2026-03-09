package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.model.enumeration.ECampingCarType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class CampingCar extends FourWheeled {
    private Double ptac;
    private Double height;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "camping_car_equipment_id")
    private CampingCarEquipment campingCarEquipment;
    private ECampingCarType campingCarType;

    @Override
    public void validate() {
        super.validate();
        if (this.getPtac() == null)
            throw new VehicleException("ptac.null", HttpStatus.BAD_REQUEST);
        if (this.getPtac() < 0 || this.getPtac()>7.5)
            throw new VehicleException("campingcar.ptac.invalid", HttpStatus.BAD_REQUEST);
        if (this.getHeight() == null)
            throw new VehicleException("campingcar.height.null", HttpStatus.BAD_REQUEST);
        if (this.getHeight() < 0)
            throw new VehicleException("campingcar.height.invalid", HttpStatus.BAD_REQUEST);
        if (this.getCampingCarEquipment() == null)
            throw new VehicleException("campingcar.equipment.null", HttpStatus.BAD_REQUEST);
        // MapStruct returns a null if the Enum Value is not found, so no additional check
        if (this.getCampingCarType() == null)
            throw new VehicleException("campingcar.type.null", HttpStatus.BAD_REQUEST);
        this.getCampingCarEquipment().validate();
    }

    @Override
    public void checkUpdateData() {
        super.checkUpdateData();
        if (this.getPtac() != null && (this.getPtac() < 0 || this.getPtac()>7.5))
            throw new VehicleException("campingcar.ptac.invalid", HttpStatus.BAD_REQUEST);
        if (this.getHeight() != null && this.getHeight() < 0)
            throw new VehicleException("campingcar.height.invalid", HttpStatus.BAD_REQUEST);
        if (this.getCampingCarEquipment() != null)
            this.getCampingCarEquipment().checkUpdateData();
    }


    public void applyPatch(CampingCar patchData) {
        super.applyPatch(patchData);
        if (patchData.getPtac() != null)
            this.setPtac(patchData.getPtac());
        if (patchData.getHeight() != null)
            this.setHeight(patchData.getHeight());
        if (this.getCampingCarEquipment() != null)
            this.getCampingCarEquipment().applyPatch(patchData.getCampingCarEquipment());
        if (patchData.getCampingCarType() != null)
            this.setCampingCarType(patchData.getCampingCarType());
    }

    public void evaluateRequiredDrivingLicence() {
        if(this.getPtac()<=3.5){
            this.setRequiredDrivingLicence(EDrivingLicence.B);
        }else if (getPtac()<=7.5){
            this.setRequiredDrivingLicence(EDrivingLicence.C1);
        }
    }
}
