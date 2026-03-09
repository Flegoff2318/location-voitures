package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface BikeService {
    BikeAdminResponseDto add(BikeRequestDto dto);

    List<BikeAdminResponseDto> findByVehicleMetaDataActive(boolean active);

    List<BikeAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);

    List<BikeAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet);

    List<BikeAdminResponseDto> findAll();

    BikeAdminResponseDto getById(UUID id);

    void delete(UUID id);

    BikeAdminResponseDto patch(UUID id, BikePatchRequestDto dto);
}
