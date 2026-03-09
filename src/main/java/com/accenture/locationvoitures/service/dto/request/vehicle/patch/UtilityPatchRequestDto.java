package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import com.accenture.locationvoitures.model.enumeration.EUtilityType;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UtilityPatchRequestDto extends FourWheeledPatchRequestDto {
    @Min(value = 0,message = "utility.haulingcapacity.invalid")
    private Double haulingCapacity;
    @Min(value = 0,message = "utility.ptac.invalid")
    private Double ptac;
    private EUtilityType utilityType;
}
