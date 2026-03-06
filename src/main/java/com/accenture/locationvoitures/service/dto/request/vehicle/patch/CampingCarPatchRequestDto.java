package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CampingCarPatchRequestDto extends FourWheeledPatchRequestDto {
    @Min(0)
    private Double ptac;
    @Min(0)
    private Double height;
    @Valid
    private CampingCarEquipmentPatchRequestDto campingCarEquipment;
    private String campingCarType;

}
