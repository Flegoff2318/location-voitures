package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.CarApi;
import com.accenture.locationvoitures.service.CarService;
import com.accenture.locationvoitures.service.dto.request.vehicle.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CarAdminResponseDto;
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
public class CarController implements CarApi {
    private final CarService carService;

    @Override
    public ResponseEntity<Void> create(CarRequestDto dto, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        CarAdminResponseDto responseDto = carService.add(dto,credentials);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();    }

    @Override
    public ResponseEntity<List<CarAdminResponseDto>> getCars(Boolean active, Boolean outoffleet, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        if(active!=null && outoffleet!=null)
            return ResponseEntity.ok(carService.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active,outoffleet,credentials));
        if(active!=null)
            return ResponseEntity.ok(carService.findByVehicleMetaDataActive(active,credentials));
        if(outoffleet!=null)
            return ResponseEntity.ok(carService.findByVehicleMetaDataOutOfFleet(outoffleet,credentials));
        return ResponseEntity.ok(carService.findAll(credentials));
    }

    @Override
    public ResponseEntity<CarAdminResponseDto> getById(UUID id, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(carService.getById(id,credentials));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        carService.delete(id,credentials);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CarAdminResponseDto> patch(UUID id, CarPatchRequestDto dto, String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(carService.patch(id,dto,credentials));
    }

    private @NonNull PersonRequestDto getCredentials(String base64Header) {
        byte[] decoded = Base64.getDecoder().decode(base64Header.split(" ")[1]);
        String[] content = new String(decoded, StandardCharsets.UTF_8).split(":");
        return new PersonRequestDto(content[0], content[1]);
    }
}
