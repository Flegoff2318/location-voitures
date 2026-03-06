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
public abstract class FourWheeledRequestDto extends VehicleRequestDto {
    @Min(1)
    @NotNull
    Integer numberOfSeats;
    @NotBlank
    String fuelType;
    @NotBlank
    String transmission;
    @NotNull
    Boolean airConditioning;
}
