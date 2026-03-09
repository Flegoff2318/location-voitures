package com.accenture.locationvoitures.service.dto.request.vehicle.patch;

import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class FourWheeledPatchRequestDto extends VehiclePatchRequestDto {
    @Min(value = 1,message = "fourwheeled.numberofseats.invalid")
    @Max(value = 16,message = "fourwheeled.numberofseats.invalid")
    protected Integer numberOfSeats;
    protected EFuelType fuelType;
    protected ETransmission transmission;
    protected Boolean airConditioning;
}
