package com.accenture.locationvoitures.service.dto.request;

import com.accenture.locationvoitures.exception.VehicleException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequestDto extends FourWheeledRequestDto {
    @NotBlank
    private String carType;

    @Min(0)
    @NotNull
    private Integer numberOfLuggage;
    @Min(3)
    @Max(5)
    @NotNull
    private Integer numberOfDoors;

    @Override
    public void verifyData() {
        super.verifyData();
        this.verifyCarTechnicalData();
    }

    private void verifyCarTechnicalData() {
        if (this.getCarType() == null || this.getCarType().isBlank())
            throw new VehicleException("Car Type is null or blank", HttpStatus.BAD_REQUEST);
        if (this.getNumberOfLuggage() == null || this.getNumberOfLuggage()<0)
            throw new VehicleException("Number of luggage is null or below 0", HttpStatus.BAD_REQUEST);
        if(this.getNumberOfDoors()==null || (this.getNumberOfDoors()!=3 && this.getNumberOfDoors()!=5))
            throw new VehicleException("Number of doors is null or not 3 or 5",HttpStatus.BAD_REQUEST);
    }
}
