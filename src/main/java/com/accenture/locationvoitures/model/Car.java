package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.model.enumeration.ECarType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car extends FourWheeled{
    private ECarType carType;
    private Integer numberOfLuggage;
    private Integer numberOfDoors;

    public void evaluateRequiredDrivingLicence(){
        if(getNumberOfSeats()<=9)
            setRequiredDrivingLicence(EDrivingLicence.B);
        if(getNumberOfSeats()>=10 && getNumberOfSeats()<=16)
            setRequiredDrivingLicence(EDrivingLicence.D1);
    }
}
