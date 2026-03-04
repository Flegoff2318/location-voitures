package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class BikePatchRequestDto extends VehiclePatchRequestDto {
    private Integer weight; // Kilograms
    private Integer frameSize; // Centimeters
    private Boolean discBrakes;

    private Boolean isElectric;
    private Integer batteryCapacity; // Watts
    private Double autonomy; // Hours, else Integer => minutes

    private String bikeType;

}
