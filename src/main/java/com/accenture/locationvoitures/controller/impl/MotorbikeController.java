package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.MotorbikeApi;
import com.accenture.locationvoitures.service.MotorbikeService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.MotorbikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.MotorbikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.MotorbikeAdminResponseDto;
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
public class MotorbikeController implements MotorbikeApi {
    private final MotorbikeService motorbikeService;

    @Override
    public ResponseEntity<Void> create(MotorbikeRequestDto dto) {
        MotorbikeAdminResponseDto responseDto = motorbikeService.add(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<MotorbikeAdminResponseDto>> getMotorbikes(Boolean active, Boolean outoffleet) {
        if (active != null && outoffleet != null)
            return ResponseEntity.ok(motorbikeService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outoffleet));
        if (active != null)
            return ResponseEntity.ok(motorbikeService.findByVehicleMetaDataActive(active));
        if (outoffleet != null)
            return ResponseEntity.ok(motorbikeService.findByVehicleMetaDataOutOfFleet(outoffleet));
        return ResponseEntity.ok(motorbikeService.findAll());
    }

    @Override
    public ResponseEntity<MotorbikeAdminResponseDto> getById(UUID id) {
        return ResponseEntity.ok(motorbikeService.getById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        motorbikeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MotorbikeAdminResponseDto> patch(UUID id, MotorbikePatchRequestDto dto) {
        return ResponseEntity.ok(motorbikeService.patch(id, dto));
    }
}
