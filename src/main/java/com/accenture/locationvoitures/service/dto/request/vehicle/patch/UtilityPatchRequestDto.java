package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

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
    @Min(0)
    Double haulingCapacity;
    @Min(1)
    Double ptac;
    String utilityType;
}
