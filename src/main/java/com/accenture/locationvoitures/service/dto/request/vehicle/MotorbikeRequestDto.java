package com.accenture.locationvoitures.service.dto.request.vehicle;

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
    @NotNull
    @Min(1)
    private Integer weight;
    @NotBlank
    private String transmission;
    @NotNull
    @Min(1)
    private Integer seatHeight; // Centimeters
    @NotNull
    @Min(1)
    private Integer power;
    @NotNull
    @Min(1)
    private Integer numberOfCylinders;
    @NotNull
    @Min(1)
    private Integer engineDisplacement; // CC
    @NotBlank
    private String motorBikeType;

}
