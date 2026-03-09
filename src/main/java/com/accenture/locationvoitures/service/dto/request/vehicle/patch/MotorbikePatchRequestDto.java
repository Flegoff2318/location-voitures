package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import com.accenture.locationvoitures.model.enumeration.EMotorbikeType;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class MotorbikePatchRequestDto extends VehiclePatchRequestDto {
    @Min(value = 1, message = "motorbike.weight.invalid")
    private Integer weight;

    private String transmission;
    @Min(value = 1, message = "motorbike.seatheight.invalid")
    private Integer seatHeight; // Centimeters
    @Min(value = 1, message = "motorbike.power.invalid")
    private Integer power;
    @Min(value = 1, message = "motorbike.cylinders.invalid")
    private Integer numberOfCylinders;
    @Min(value = 1, message = "motorbike.enginedisplacement.invalid")
    private Integer engineDisplacement; // CC

    private EMotorbikeType motorbikeType;


}
