package com.accenture.locationvoitures.service.dto.request.vehicle;

import com.accenture.locationvoitures.model.enumeration.EUtilityType;
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
    @NotNull(message = "utility.haulingcapacity.null")
    @Min(value = 0,message = "utility.haulingcapacity.invalid")
    Double haulingCapacity;
    @NotNull(message = "utility.ptac.null")
    @Min(value = 0,message = "utility.ptac.invalid")
    Double ptac;
    @NotNull(message = "utility.type.null")
    EUtilityType utilityType;
}
