package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.BikeApi;
import com.accenture.locationvoitures.service.BikeService;
import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@AllArgsConstructor
public class BikeController implements BikeApi {
    private final BikeService bikeService;

    @Override
    public ResponseEntity<Void> create(BikeRequestDto dto) {
        BikeAdminResponseDto responseDto = bikeService.add(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<BikeAdminResponseDto>> getBikes(Boolean active, Boolean outoffleet) {
        if (active != null && outoffleet != null)
            return ResponseEntity.ok(bikeService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outoffleet));
        if (active != null)
            return ResponseEntity.ok(bikeService.findByVehicleMetaDataActive(active));
        if (outoffleet != null)
            return ResponseEntity.ok(bikeService.findByVehicleMetaDataOutOfFleet(outoffleet));
        return ResponseEntity.ok(bikeService.findAll());
    }

    @Override
    public ResponseEntity<BikeAdminResponseDto> getById(UUID id) {
        return ResponseEntity.ok(bikeService.getById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        bikeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BikeAdminResponseDto> patch(UUID id, BikePatchRequestDto dto) {
        return ResponseEntity.ok(bikeService.patch(id, dto));
    }
}
