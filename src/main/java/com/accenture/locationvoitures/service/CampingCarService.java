package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.CampingCarRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CampingCarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CampingCarAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface CampingCarService {
    CampingCarAdminResponseDto add(CampingCarRequestDto dto, PersonRequestDto credentials);

    List<CampingCarAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials);

    List<CampingCarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials);

    List<CampingCarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials);

    List<CampingCarAdminResponseDto> findAll(PersonRequestDto credentials);

    CampingCarAdminResponseDto getById(UUID id, PersonRequestDto credentials);

    void delete(UUID id, PersonRequestDto credentials);

    CampingCarAdminResponseDto patch(UUID id, CampingCarPatchRequestDto dto, PersonRequestDto credentials);
}
