package com.accenture.locationvoitures.service.dto.request.vehicle;

import com.accenture.locationvoitures.model.enumeration.ECarType;
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
public final class CarRequestDto extends FourWheeledRequestDto {
    @NotNull(message = "car.type.null")
    private ECarType carType;
    @Min(value = 0,message = "car.luggage.invalid")
    @NotNull(message = "car.luggage.null")
    private Integer numberOfLuggage;
    @Min(value = 3,message = "car.doors.invalid")
    @Max(value = 5,message = "car.doors.invalid")
    @NotNull(message = "car.doors.null")
    private Integer numberOfDoors;
}
