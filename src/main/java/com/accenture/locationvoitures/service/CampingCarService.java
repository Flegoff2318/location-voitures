package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.CampingCarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CampingCarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CampingCarAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface CampingCarService {
    CampingCarAdminResponseDto add(CampingCarRequestDto dto );

    List<CampingCarAdminResponseDto> findByVehicleMetaDataActive(boolean active);

    List<CampingCarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);

    List<CampingCarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet);

    List<CampingCarAdminResponseDto> findAll();

    CampingCarAdminResponseDto getById(UUID id);

    void delete(UUID id);

    CampingCarAdminResponseDto patch(UUID id, CampingCarPatchRequestDto dto);
}
