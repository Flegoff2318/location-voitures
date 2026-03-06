package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class VehiclePatchRequestDto {
    protected String brand;
    protected String model;
    protected String color;
    @Valid
    protected VehicleMetaDataPatchRequestDto vehicleMetaData;

}
