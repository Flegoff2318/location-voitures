package com.accenture.locationvoitures.service.dto.request;

import com.accenture.locationvoitures.exception.VehicleException;
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
public abstract class FourWheeledRequestDto extends VehicleRequestDto {
    @Min(1)
    @NotNull
    Integer numberOfSeats;
    @NotBlank
    String fuelType;
    @NotBlank
    String transmission;
    @NotNull
    Boolean airConditioning;

    @Override
    public void verifyData() {
        super.verifyData();
        this.verifyFourWheeledTechnicalData();
    }

    private void verifyFourWheeledTechnicalData() {
        if (this.getNumberOfSeats() == null || this.getNumberOfSeats() < 1)
            throw new VehicleException("Number of seats is null or below 1", HttpStatus.BAD_REQUEST);
        if (this.getFuelType() == null || this.getFuelType().isBlank())
            throw new VehicleException("Fuel Type is null or blank", HttpStatus.BAD_REQUEST);
        if (this.getTransmission() == null || this.getTransmission().isBlank())
            throw new VehicleException("Transmission is null or blank", HttpStatus.BAD_REQUEST);
        if (this.getAirConditioning() == null)
            throw new VehicleException("Air Conditioning is null", HttpStatus.BAD_REQUEST);
    }
}
