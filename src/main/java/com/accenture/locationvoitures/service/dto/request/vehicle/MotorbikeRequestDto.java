package com.accenture.locationvoitures.service.dto.request.vehicle;

import com.accenture.locationvoitures.model.enumeration.EMotorbikeType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class MotorbikeRequestDto extends VehicleRequestDto {
    @NotNull(message = "motorbike.weight.null")
    @Min(value = 1,message = "motorbike.weight.invalid")
    private Integer weight;
    @NotNull(message = "transmission.null")
    private ETransmission transmission;
    @NotNull(message = "motorbike.seatheight.null")
    @Min(value = 1,message = "motorbike.seatheight.invalid")
    private Integer seatHeight; // Centimeters
    @NotNull(message = "motorbike.power.null")
    @Min(value = 1,message = "motorbike.power.invalid")
    private Integer power;
    @NotNull(message = "motorbike.cylinders.null")
    @Min(value = 1,message = "motorbike.cylinders.invalid")
    private Integer numberOfCylinders;
    @NotNull(message = "motorbike.enginedisplacement.null")
    @Min(value = 1,message = "motorbike.enginedisplacement.invalid")
    private Integer engineDisplacement; // CC
    @NotNull(message = "motorbike.type.null")
    private EMotorbikeType motorBikeType;

}
