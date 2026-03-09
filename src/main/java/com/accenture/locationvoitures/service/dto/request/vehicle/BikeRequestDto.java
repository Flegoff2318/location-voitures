package com.accenture.locationvoitures.service.dto.request.vehicle;

import com.accenture.locationvoitures.model.enumeration.EBikeType;
import io.swagger.v3.oas.annotations.media.Schema;
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
public final class BikeRequestDto extends VehicleRequestDto {
    @NotNull(message = "bike.weight.null")
    @Min(value = 1,message = "bike.weight.invalid")
    private Integer weight; // Kilograms
    @NotNull(message = "bike.framesize.null")
    @Min(value = 1,message = "bike.framesize.invalid")
    private Integer frameSize; // Centimeters
    @NotNull(message = "bike.discbrakes.null")
    private Boolean discBrakes;
    @NotNull(message = "bike.iselectric.null")
    private Boolean isElectric;
    @Min(value = 1,message = "bike.batterycapacity.invalid")
    private Integer batteryCapacity; // Watts
    @Min(value = 1,message = "bike.autonomy.invalid")
    private Double autonomy; // Hours, else Integer => minutes

    @NotNull(message = "bike.type.null")
    private EBikeType bikeType;

    public BikeRequestDto(String brand, String model, String color, VehicleMetaDataRequestDto vehicleMetaData, Integer weight, Integer frameSize, Boolean discBrakes, Boolean isElectric, Integer batteryCapacity, Double autonomy, EBikeType bikeType) {
        super(brand, model, color, vehicleMetaData);
        this.weight = weight;
        this.frameSize = frameSize;
        this.discBrakes = discBrakes;
        this.isElectric = isElectric;
        this.batteryCapacity = batteryCapacity;
        this.autonomy = autonomy;
        this.bikeType = bikeType;
    }
}
