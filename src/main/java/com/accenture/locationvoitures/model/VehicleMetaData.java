package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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

    public void validate() {
        if(this.getDailyRentalPrice()==null)
            throw new VehicleException("metadata.dailyrentalprice.null", HttpStatus.BAD_REQUEST);
        if(this.getDailyRentalPrice()<=0)
            throw new VehicleException("metadata.dailyrentalprice.invalid", HttpStatus.BAD_REQUEST);
        if(this.getMileage()==null)
            throw new VehicleException("metadata.mileage.null",HttpStatus.BAD_REQUEST);
        if(this.getMileage()<0)
            throw new VehicleException("metadata.mileage.invalid",HttpStatus.BAD_REQUEST);
        if(this.getActive()==null)
            throw new VehicleException("metadata.active.null",HttpStatus.BAD_REQUEST);
        if(this.getOutOfFleet()==null)
            throw new VehicleException("metadata.outoffleet.null",HttpStatus.BAD_REQUEST);
        if(this.getOutOfFleet() && getActive())
            throw new VehicleException("metadata.activeoutoffleet",HttpStatus.BAD_REQUEST);
    }
    public void checkUpdateData(){
        if(this.getDailyRentalPrice()!=null && this.getDailyRentalPrice()<=0)
            throw new VehicleException("metadata.dailyrentalprice.invalid", HttpStatus.BAD_REQUEST);
        if(this.getMileage()!=null && this.getMileage()<0)
            throw new VehicleException("metadata.mileage.invalid",HttpStatus.BAD_REQUEST);
        if(this.getOutOfFleet() && getActive())
            throw new VehicleException("metadata.activeoutoffleet",HttpStatus.BAD_REQUEST);
    }

    public void applyPatch(VehicleMetaData vehicleMetaData) {
        if(vehicleMetaData.getDailyRentalPrice()!=null)
            this.setDailyRentalPrice(vehicleMetaData.getDailyRentalPrice());
        if(vehicleMetaData.getMileage()!=null)
            this.setMileage(vehicleMetaData.getMileage());
        if(vehicleMetaData.getActive()!=null)
            this.setActive(vehicleMetaData.getActive());
        if(vehicleMetaData.getOutOfFleet()!=null)
            this.setOutOfFleet(vehicleMetaData.getOutOfFleet());
    }
}
