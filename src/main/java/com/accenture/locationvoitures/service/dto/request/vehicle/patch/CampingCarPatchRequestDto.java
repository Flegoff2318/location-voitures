package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import com.accenture.locationvoitures.model.enumeration.ECampingCarType;
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
    @Min(value = 0,message = "campingcar.ptac.invalid")
    private Double ptac;
    @Min(value = 1,message = "campingcar.height.invalid")
    private Double height;
    @Valid
    private CampingCarEquipmentPatchRequestDto campingCarEquipment;
    private ECampingCarType campingCarType;

}
