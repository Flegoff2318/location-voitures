package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class FourWheeledPatchRequestDto extends VehiclePatchRequestDto {
    @Min(1)
    Integer numberOfSeats;
    String fuelType;
    String transmission;
    Boolean airConditioning;
}
