package com.accenture.locationvoitures.service.dto.request.vehicle;

import com.accenture.locationvoitures.model.enumeration.ECampingCarType;
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
    @NotNull(message = "campingcar.ptac.null")
    @Min(value = 0,message = "campingcar.ptac.invalid")
    private Double ptac;
    @NotNull(message = "campingcar.height.null")
    @Min(value = 1,message = "campingcar.height.invalid")
    private Double height;
    @Valid
    @NotNull(message = "campingcar.equipment.null")
    private CampingCarEquipmentRequestDto campingCarEquipment;
    @NotNull(message = "campingcar.type.null")
    private ECampingCarType campingCarType;
}
