package com.accenture.locationvoitures.service.dto.request.vehicle;

import jakarta.validation.Valid;
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
public final class CampingCarRequestDto extends FourWheeledRequestDto {
    @NotNull
    @Min(0)
    private Double ptac;
    @NotNull
    @Min(0)
    private Double height;
    @Valid
    @NotNull
    private CampingCarEquipmentRequestDto campingCarEquipment;
    @NotBlank
    private String campingCarType;
}
