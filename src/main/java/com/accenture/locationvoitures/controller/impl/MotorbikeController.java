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
    public ResponseEntity<Void> create(MotorbikeRequestDto dto, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        MotorbikeAdminResponseDto responseDto = motorbikeService.add(dto, credentials);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<MotorbikeAdminResponseDto>> getMotorbikes(Boolean active, Boolean outoffleet, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        if (active != null && outoffleet != null)
            return ResponseEntity.ok(motorbikeService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outoffleet, credentials));
        if (active != null)
            return ResponseEntity.ok(motorbikeService.findByVehicleMetaDataActive(active, credentials));
        if (outoffleet != null)
            return ResponseEntity.ok(motorbikeService.findByVehicleMetaDataOutOfFleet(outoffleet, credentials));
        return ResponseEntity.ok(motorbikeService.findAll(credentials));
    }

    @Override
    public ResponseEntity<MotorbikeAdminResponseDto> getById(UUID id, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(motorbikeService.getById(id, credentials));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        motorbikeService.delete(id, credentials);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MotorbikeAdminResponseDto> patch(UUID id, MotorbikePatchRequestDto dto, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(motorbikeService.patch(id, dto, credentials));
    }

    private @NonNull PersonRequestDto getCredentials(String base64Header) {
        byte[] decoded = Base64.getDecoder().decode(base64Header.split(" ")[1]);
        String[] content = new String(decoded, StandardCharsets.UTF_8).split(":");
        return new PersonRequestDto(content[0], content[1]);
    }
}
