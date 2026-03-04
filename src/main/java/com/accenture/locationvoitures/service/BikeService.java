package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;

import java.util.List;
import java.util.UUID;

public interface BikeService {
    BikeAdminResponseDto add(BikeRequestDto dto, PersonRequestDto credentials);

    List<BikeAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials);

    List<BikeAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials);

    List<BikeAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials);

    List<BikeAdminResponseDto> findAll(PersonRequestDto credentials);

    BikeAdminResponseDto getById(UUID id, PersonRequestDto credentials);

    void delete(UUID id, PersonRequestDto credentials);

    BikeAdminResponseDto patch(UUID id, BikePatchRequestDto dto, PersonRequestDto credentials);
}
