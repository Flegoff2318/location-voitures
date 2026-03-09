package com.accenture.locationvoitures.service.dto.request.vehicle;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class VehicleRequestDto {
    @NotBlank(message = "vehicle.brand.blank")
    protected String brand;
    @NotBlank(message = "vehicle.model.blank")
    protected String model;
    @NotBlank(message = "vehicle.color.blank")
    protected String color;
    @Valid
    @NotNull(message = "vehicle.metadata.null")
    protected VehicleMetaDataRequestDto vehicleMetaData;
}
