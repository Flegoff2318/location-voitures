package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class FourWheeled extends Vehicle {
    private Integer numberOfSeats;
    private EFuelType fuelType;
    private ETransmission transmission;
    private Boolean airConditioning;

    @Override
    public void validate() {
        super.validate();
        if (this.getNumberOfSeats() == null)
            throw new VehicleException("Number of seats is null", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfSeats() < 1)
            throw new VehicleException("Number of seats must superior to 0", HttpStatus.BAD_REQUEST);
        // MapStruct returns a null if the Enum Value is not found, so no additional check
        if(this.getFuelType()==null)
            throw new VehicleException("Fuel type is null",HttpStatus.BAD_REQUEST);
        if(this.getTransmission()==null)
            throw new VehicleException("Transmission is null",HttpStatus.BAD_REQUEST);
        if(this.getAirConditioning()==null)
            throw new VehicleException("Air conditioning is null",HttpStatus.BAD_REQUEST);
    }

    @Override
    public void checkUpdateData(){
        super.checkUpdateData();
        if (this.getNumberOfSeats() != null && this.getNumberOfSeats() < 1)
            throw new VehicleException("Number of seats must superior to 0", HttpStatus.BAD_REQUEST);
    }

    public void applyPatch(FourWheeled patchData) {
        super.applyPatch(patchData);
        if(patchData.getNumberOfSeats()!=null)
            this.setNumberOfSeats(patchData.getNumberOfSeats());
        if(patchData.getFuelType()!=null)
            this.setFuelType(patchData.getFuelType());
        if(patchData.getTransmission()!=null)
            this.setTransmission(patchData.getTransmission());
        if(patchData.getAirConditioning()!=null)
            this.setAirConditioning(patchData.getAirConditioning());
    }
}
