package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CarAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface CarService {
    CarAdminResponseDto add(CarRequestDto dto, PersonRequestDto credentials);

    List<CarAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials);

    List<CarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials);

    List<CarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials);

    List<CarAdminResponseDto> findAll(PersonRequestDto credentials);
    CarAdminResponseDto getById(UUID id, PersonRequestDto credentials);
    void delete(UUID id,PersonRequestDto credentials);
    CarAdminResponseDto patch(UUID id, CarPatchRequestDto dto, PersonRequestDto credentials);
}
