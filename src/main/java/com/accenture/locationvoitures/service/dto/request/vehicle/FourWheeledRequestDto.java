package com.accenture.locationvoitures.service.dto.request.vehicle;

import com.accenture.locationvoitures.model.enumeration.EFuelType;
import com.accenture.locationvoitures.model.enumeration.ETransmission;
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
public abstract class FourWheeledRequestDto extends VehicleRequestDto {
    @Min(value = 1,message = "fourwheeled.numberofseats.invalid")
    @Max(value = 16,message = "fourwheeled.numberofseats.invalid")
    @NotNull(message = "fourwheeled.numberofseats.null")
    protected Integer numberOfSeats;
    @NotNull(message = "fueltype.null")
    protected EFuelType fuelType;
    @NotNull(message = "transmission.null")
    protected ETransmission transmission;
    @NotNull(message = "fourwheeled.airconditioning.null")
    protected Boolean airConditioning;
}
