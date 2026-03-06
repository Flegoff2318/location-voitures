package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.BikeApi;
import com.accenture.locationvoitures.service.BikeService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;
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
public class BikeController implements BikeApi {
    private final BikeService bikeService;

    @Override
    public ResponseEntity<Void> create(BikeRequestDto dto, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        BikeAdminResponseDto responseDto = bikeService.add(dto, credentials);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<BikeAdminResponseDto>> getBikes(Boolean active, Boolean outoffleet, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        if (active != null && outoffleet != null)
            return ResponseEntity.ok(bikeService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outoffleet, credentials));
        if (active != null)
            return ResponseEntity.ok(bikeService.findByVehicleMetaDataActive(active, credentials));
        if (outoffleet != null)
            return ResponseEntity.ok(bikeService.findByVehicleMetaDataOutOfFleet(outoffleet, credentials));
        return ResponseEntity.ok(bikeService.findAll(credentials));
    }

    @Override
    public ResponseEntity<BikeAdminResponseDto> getById(UUID id, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(bikeService.getById(id, credentials));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        bikeService.delete(id, credentials);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BikeAdminResponseDto> patch(UUID id, BikePatchRequestDto dto, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(bikeService.patch(id, dto, credentials));
    }

    private @NonNull PersonRequestDto getCredentials(String base64Header) {
        byte[] decoded = Base64.getDecoder().decode(base64Header.split(" ")[1]);
        String[] content = new String(decoded, StandardCharsets.UTF_8).split(":");
        return new PersonRequestDto(content[0], content[1]);
    }
}
