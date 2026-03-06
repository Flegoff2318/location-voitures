package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class MotorbikePatchRequestDto extends VehiclePatchRequestDto {

    private Integer weight;

    private String transmission;

    private Integer seatHeight; // Centimeters

    private Integer power;

    private Integer numberOfCylinders;

    private Integer engineDisplacement; // CC

    private String motorBikeType;


}
