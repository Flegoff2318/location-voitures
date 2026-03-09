package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.CampingCarApi;
import com.accenture.locationvoitures.service.CampingCarService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.CampingCarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CampingCarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CampingCarAdminResponseDto;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CampingCarController implements CampingCarApi {
    private final CampingCarService campingCarService;

    @Override
    public ResponseEntity<Void> create(CampingCarRequestDto dto) {
        
        CampingCarAdminResponseDto responseDto = campingCarService.add(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<CampingCarAdminResponseDto>> getCampingCars(Boolean active, Boolean outoffleet) {
        
        if (active != null && outoffleet != null)
            return ResponseEntity.ok(campingCarService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outoffleet));
        if (active != null)
            return ResponseEntity.ok(campingCarService.findByVehicleMetaDataActive(active));
        if (outoffleet != null)
            return ResponseEntity.ok(campingCarService.findByVehicleMetaDataOutOfFleet(outoffleet));
        return ResponseEntity.ok(campingCarService.findAll());
    }

    @Override
    public ResponseEntity<CampingCarAdminResponseDto> getById(UUID id) {
        
        return ResponseEntity.ok(campingCarService.getById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        
        campingCarService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CampingCarAdminResponseDto> patch(UUID id, CampingCarPatchRequestDto dto) {
        
        return ResponseEntity.ok(campingCarService.patch(id, dto));
    }
}
