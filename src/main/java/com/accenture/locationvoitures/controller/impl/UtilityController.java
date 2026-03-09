package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.UtilityApi;
import com.accenture.locationvoitures.service.UtilityService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.UtilityRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.UtilityPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.UtilityAdminResponseDto;
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
public class UtilityController implements UtilityApi {
    private final UtilityService utilityService;

    @Override
    public ResponseEntity<Void> create(UtilityRequestDto dto) {
        UtilityAdminResponseDto responseDto = utilityService.add(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<UtilityAdminResponseDto>> getUtilities(Boolean active, Boolean outoffleet) {
        if (active != null && outoffleet != null)
            return ResponseEntity.ok(utilityService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outoffleet));
        if (active != null)
            return ResponseEntity.ok(utilityService.findByVehicleMetaDataActive(active));
        if (outoffleet != null)
            return ResponseEntity.ok(utilityService.findByVehicleMetaDataOutOfFleet(outoffleet));
        return ResponseEntity.ok(utilityService.findAll());
    }

    @Override
    public ResponseEntity<UtilityAdminResponseDto> getById(UUID id) {
        return ResponseEntity.ok(utilityService.getById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        utilityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UtilityAdminResponseDto> patch(UUID id, UtilityPatchRequestDto dto) {
        return ResponseEntity.ok(utilityService.patch(id, dto));
    }
}
