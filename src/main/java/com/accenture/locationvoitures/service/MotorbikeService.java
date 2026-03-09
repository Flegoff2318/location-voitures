package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.MotorbikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.MotorbikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.MotorbikeAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface MotorbikeService {
    MotorbikeAdminResponseDto add(MotorbikeRequestDto dto);

    List<MotorbikeAdminResponseDto> findByVehicleMetaDataActive(boolean active);

    List<MotorbikeAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);

    List<MotorbikeAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet);

    List<MotorbikeAdminResponseDto> findAll();

    MotorbikeAdminResponseDto getById(UUID id);

    void delete(UUID id);

    MotorbikeAdminResponseDto patch(UUID id, MotorbikePatchRequestDto dto);
}
