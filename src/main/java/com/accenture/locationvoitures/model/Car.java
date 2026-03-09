package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.model.enumeration.ECarType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
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
public class Car extends FourWheeled {
    private ECarType carType;
    private Integer numberOfLuggage;
    private Integer numberOfDoors;

    public void evaluateRequiredDrivingLicence() {
        if (getNumberOfSeats() <= 9)
            setRequiredDrivingLicence(EDrivingLicence.B);
        if (getNumberOfSeats() >= 10 && getNumberOfSeats() <= 16)
            setRequiredDrivingLicence(EDrivingLicence.D1);
    }

    @Override
    public void validate() {
        super.validate();
        // MapStruct returns a null if the Enum Value is not found, so no additional check
        if (this.getCarType() == null)
            throw new VehicleException("car.type.null", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfLuggage() == null)
            throw new VehicleException("car.luggage.null", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfLuggage() < 0)
            throw new VehicleException("car.luggage.invalid", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfDoors() == null)
            throw new VehicleException("car.doors.null", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfDoors() != 3 && this.getNumberOfDoors() != 5)
            throw new VehicleException("car.doors.invalid", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void checkUpdateData() {
        super.checkUpdateData();
        if (this.getNumberOfLuggage() != null && this.getNumberOfLuggage() < 0)
            throw new VehicleException("car.luggage.invalid", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfDoors() != null && this.getNumberOfDoors() != 3 && this.getNumberOfDoors() != 5)
            throw new VehicleException("car.doors.invalid", HttpStatus.BAD_REQUEST);
    }

    public void applyPatch(Car patchData) {
        super.applyPatch(patchData);
        if (patchData.getCarType() != null)
            this.setCarType(patchData.getCarType());
        if (patchData.getNumberOfLuggage() != null)
            this.setNumberOfLuggage(patchData.getNumberOfLuggage());
        if (patchData.getNumberOfDoors() != null)
            this.setNumberOfDoors(patchData.getNumberOfDoors());
    }
}
