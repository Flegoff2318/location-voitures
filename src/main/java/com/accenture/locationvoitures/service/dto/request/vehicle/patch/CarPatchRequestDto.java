package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import com.accenture.locationvoitures.model.enumeration.ECarType;
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
    private ECarType carType;
    @Min(value = 0,message = "car.luggage.invalid")
    private Integer numberOfLuggage;
    @Min(value = 3,message = "car.doors.invalid")
    @Max(value = 5,message = "car.doors.invalid")
    private Integer numberOfDoors;
}
