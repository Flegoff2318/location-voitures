package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.model.Bike;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.repository.BikeRepository;
import com.accenture.locationvoitures.service.BikeService;
import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.BikeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class BikeServiceImpl implements BikeService {
    private static final String BIKE_NOT_FOUND = "Bike not found";
    private final BikeRepository bikeRepository;
    private final BikeMapper bikeMapper;

    @Override
    public BikeAdminResponseDto add(BikeRequestDto dto) {
        if(dto==null)
            throw new VehicleException("dto.null", HttpStatus.BAD_REQUEST);
        Bike bike = bikeMapper.toEntity(dto);
        bike.validate();
        bike.setRequiredDrivingLicence(EDrivingLicence.NONE);
        Bike saved = bikeRepository.save(bike);
        return bikeMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BikeAdminResponseDto> findByVehicleMetaDataActive(boolean active) {
        return bikeRepository.findByVehicleMetaDataActive(active).stream()
                .map(bikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BikeAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet) {
        return bikeRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(bikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BikeAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet) {
        return bikeRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(bikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BikeAdminResponseDto> findAll() {
        return bikeRepository.findAll().stream()
                .map(bikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BikeAdminResponseDto getById(UUID id) {
        Bike bike = bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BIKE_NOT_FOUND));
        return bikeMapper.toAdminResponseDto(bike);
    }

    @Override
    public void delete(UUID id) {
        bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BIKE_NOT_FOUND));

        bikeRepository.deleteById(id);
    }

    @Override
    public BikeAdminResponseDto patch(UUID id, BikePatchRequestDto dto) {
        Bike old = bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BIKE_NOT_FOUND));
        Bike patchData = bikeMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        Bike patched = bikeRepository.save(old);
        return bikeMapper.toAdminResponseDto(patched);
    }
}
