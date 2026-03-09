package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.CarApi;
import com.accenture.locationvoitures.service.CarService;
import com.accenture.locationvoitures.service.dto.request.vehicle.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CarAdminResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CarController implements CarApi {
    private final CarService carService;

    @Override
    public ResponseEntity<Void> create(CarRequestDto dto) {
        CarAdminResponseDto responseDto = carService.add(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<CarAdminResponseDto>> getCars(Boolean active, Boolean outoffleet) {
        if (active != null && outoffleet != null)
            return ResponseEntity.ok(carService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outoffleet));
        if (active != null)
            return ResponseEntity.ok(carService.findByVehicleMetaDataActive(active));
        if (outoffleet != null)
            return ResponseEntity.ok(carService.findByVehicleMetaDataOutOfFleet(outoffleet));
        return ResponseEntity.ok(carService.findAll());
    }

    @Override
    public ResponseEntity<CarAdminResponseDto> getById(UUID id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CarAdminResponseDto> patch(UUID id, CarPatchRequestDto dto) {
        return ResponseEntity.ok(carService.patch(id, dto));
    }
}
