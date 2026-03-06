package com.accenture.locationvoitures.service.dto.request.vehicle;

import jakarta.validation.constraints.Max;
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
public final class CarRequestDto extends FourWheeledRequestDto {
    @NotBlank
    private String carType;
    @Min(0)
    @NotNull
    private Integer numberOfLuggage;
    @Min(3)
    @Max(5)
    @NotNull
    private Integer numberOfDoors;
}
