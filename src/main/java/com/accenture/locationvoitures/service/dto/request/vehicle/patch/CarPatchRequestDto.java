package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CarPatchRequestDto extends FourWheeledPatchRequestDto {
    private String carType;
    @Min(0)
    private Integer numberOfLuggage;
    @Min(3)
    @Max(5)
    private Integer numberOfDoors;
}
