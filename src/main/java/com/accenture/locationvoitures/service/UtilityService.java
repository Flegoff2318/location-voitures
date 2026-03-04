package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.UtilityRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.UtilityPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.UtilityAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface UtilityService {
    UtilityAdminResponseDto add(UtilityRequestDto dto, PersonRequestDto credentials);

    List<UtilityAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials);

    List<UtilityAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials);

    List<UtilityAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials);

    List<UtilityAdminResponseDto> findAll(PersonRequestDto credentials);

    UtilityAdminResponseDto getById(UUID id, PersonRequestDto credentials);

    void delete(UUID id, PersonRequestDto credentials);

    UtilityAdminResponseDto patch(UUID id, UtilityPatchRequestDto dto, PersonRequestDto credentials);
}
