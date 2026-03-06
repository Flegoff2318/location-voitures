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
public final class UtilityRequestDto extends FourWheeledRequestDto {
    @NotNull
    @Min(0)
    Double haulingCapacity;
    @NotNull
    @Min(1)
    Double ptac;
    @NotBlank
    String utilityType;
}
