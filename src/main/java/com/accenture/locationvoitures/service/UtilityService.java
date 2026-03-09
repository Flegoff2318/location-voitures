package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.UtilityRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.UtilityPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.UtilityAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface UtilityService {
    UtilityAdminResponseDto add(UtilityRequestDto dto);

    List<UtilityAdminResponseDto> findByVehicleMetaDataActive(boolean active);

    List<UtilityAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);

    List<UtilityAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet);

    List<UtilityAdminResponseDto> findAll();

    UtilityAdminResponseDto getById(UUID id);

    void delete(UUID id);

    UtilityAdminResponseDto patch(UUID id, UtilityPatchRequestDto dto);
}
