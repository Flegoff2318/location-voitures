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
    @NotBlank(message = "Brand is null or blank")
    String brand;
    @NotBlank
    String model;
    @NotBlank
    String color;
    @Valid
    @NotNull
    VehicleMetaDataRequestDto vehicleMetaData;
}
