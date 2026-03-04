package com.accenture.locationvoitures.service.dto.request.vehicle;

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
public final class BikeRequestDto extends VehicleRequestDto {
    @NotNull
    private Integer weight; // Kilograms
    @NotNull
    private Integer frameSize; // Centimeters
    @NotNull
    private Boolean discBrakes;
    @NotNull
    private Boolean isElectric;
    private Integer batteryCapacity; // Watts
    private Double autonomy; // Hours, else Integer => minutes

    @NotBlank
    private String bikeType;
}
