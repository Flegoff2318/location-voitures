package com.accenture.locationvoitures.service.dto.request;

import com.accenture.locationvoitures.exception.VehicleException;
import org.springframework.http.HttpStatus;

public record VehicleMetaDataRequestDto(
        Double dailyRentalPrice,
        Integer mileage,
        Boolean active,
        Boolean outOfFleet
) {
    public void verifyData(){
        if (this.dailyRentalPrice() == null || this.dailyRentalPrice() < 0)
            throw new VehicleException("Daily Rental Price is null or blank", HttpStatus.BAD_REQUEST);
        if (this.mileage() == null || this.mileage() < 0)
            throw new VehicleException("Mileage is null or below 0", HttpStatus.BAD_REQUEST);
        if (this.active() == null)
            throw new VehicleException("Active is null", HttpStatus.BAD_REQUEST);
        if (this.outOfFleet() == null)
            throw new VehicleException("Out of Fleet is null or blank", HttpStatus.BAD_REQUEST);
    }
}
