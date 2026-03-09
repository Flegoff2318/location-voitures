package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CarAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface CarService {
    CarAdminResponseDto add(CarRequestDto dto);

    List<CarAdminResponseDto> findByVehicleMetaDataActive(boolean active);

    List<CarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);

    List<CarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet);

    List<CarAdminResponseDto> findAll();
    CarAdminResponseDto getById(UUID id);
    void delete(UUID id);
    CarAdminResponseDto patch(UUID id, CarPatchRequestDto dto);
}
