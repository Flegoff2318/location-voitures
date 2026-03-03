package com.accenture.locationvoitures.service.dto.request;

import com.accenture.locationvoitures.exception.VehicleException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class VehicleRequestDto {
    @NotBlank
    protected String brand;
    @NotBlank
    protected String model;
    @NotBlank
    protected String color;
    @Valid
    @NotNull
    protected VehicleMetaDataRequestDto vehicleMetaData;

    public void verifyData() {
        this.verifyVehicleTechnicalData();
        this.getVehicleMetaData().verifyData();
    }

    /**
     *
     */
    private void verifyVehicleTechnicalData() {
        if (this.getBrand() == null || this.getBrand().isBlank())
            throw new VehicleException("Brand is null or blank", HttpStatus.BAD_REQUEST);
        if (this.getModel() == null || this.getModel().isBlank())
            throw new VehicleException("Model is null or blank", HttpStatus.BAD_REQUEST);
        if (this.getColor() == null || this.getColor().isBlank())
            throw new VehicleException("Color is null or blank", HttpStatus.BAD_REQUEST);
        if (this.getVehicleMetaData() == null)
            throw new VehicleException("Vehicle Meta Data is null", HttpStatus.BAD_REQUEST);
    }
}
