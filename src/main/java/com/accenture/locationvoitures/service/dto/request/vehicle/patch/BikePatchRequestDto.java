package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import com.accenture.locationvoitures.model.enumeration.EBikeType;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class BikePatchRequestDto extends VehiclePatchRequestDto {
    @Min(value = 1,message = "bike.weight.invalid")
    private Integer weight; // Kilograms
    @Min(value = 1,message = "bike.framesize.invalid")
    private Integer frameSize; // Centimeters
    private Boolean discBrakes;

    private Boolean isElectric;
    @Min(value = 1,message = "bike.batterycapacity.invalid")
    private Integer batteryCapacity; // Watts
    @Min(value = 1,message = "bike.autonomy.invalid")
    private Double autonomy; // Hours, else Integer => minutes

    private EBikeType bikeType;

}
