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
    public ResponseEntity<Void> create(UtilityRequestDto dto, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        UtilityAdminResponseDto responseDto = utilityService.add(dto,credentials);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<UtilityAdminResponseDto>> getUtilities(Boolean active, Boolean outoffleet, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        if(active!=null && outoffleet!=null)
            return ResponseEntity.ok(utilityService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active,outoffleet,credentials));
        if(active!=null)
            return ResponseEntity.ok(utilityService.findByVehicleMetaDataActive(active,credentials));
        if(outoffleet!=null)
            return ResponseEntity.ok(utilityService.findByVehicleMetaDataOutOfFleet(outoffleet,credentials));
        return ResponseEntity.ok(utilityService.findAll(credentials));
    }

    @Override
    public ResponseEntity<UtilityAdminResponseDto> getById(UUID id, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(utilityService.getById(id,credentials));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        utilityService.delete(id,credentials);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UtilityAdminResponseDto> patch(UUID id, UtilityPatchRequestDto dto, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(utilityService.patch(id,dto,credentials));
    }

    private @NonNull PersonRequestDto getCredentials(String base64Header) {
        byte[] decoded = Base64.getDecoder().decode(base64Header.split(" ")[1]);
        String[] content = new String(decoded, StandardCharsets.UTF_8).split(":");
        return new PersonRequestDto(content[0], content[1]);
    }
}
