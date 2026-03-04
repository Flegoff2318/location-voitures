package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.MotorbikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.MotorbikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.MotorbikeAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface MotorbikeService {
    MotorbikeAdminResponseDto add(MotorbikeRequestDto dto, PersonRequestDto credentials);

    List<MotorbikeAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials);

    List<MotorbikeAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials);

    List<MotorbikeAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials);

    List<MotorbikeAdminResponseDto> findAll(PersonRequestDto credentials);

    MotorbikeAdminResponseDto getById(UUID id, PersonRequestDto credentials);

    void delete(UUID id, PersonRequestDto credentials);

    MotorbikeAdminResponseDto patch(UUID id, MotorbikePatchRequestDto dto, PersonRequestDto credentials);
}
