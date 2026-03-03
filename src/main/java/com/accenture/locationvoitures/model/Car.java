package com.accenture.locationvoitures.model;

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
    private CarType carType;
    private Integer numberOfLuggage;
    private Integer numberOfDoors;

    public void evaluateRequiredDrivingLicence(){
        if(getNumberOfSeats()<=9)
            setRequiredDrivingLicence(DrivingLicence.B);
        if(getNumberOfSeats()>=10 && getNumberOfSeats()<=16)
            setRequiredDrivingLicence(DrivingLicence.D1);
    }
}
