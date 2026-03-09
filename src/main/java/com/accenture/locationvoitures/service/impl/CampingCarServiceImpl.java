package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.model.CampingCar;
import com.accenture.locationvoitures.repository.CampingCarRepository;
import com.accenture.locationvoitures.service.CampingCarService;
import com.accenture.locationvoitures.service.dto.request.vehicle.CampingCarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CampingCarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CampingCarAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.CampingCarMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class CampingCarServiceImpl implements CampingCarService {
    private static final String CAMPING_CAR_NOT_FOUND = "CampingCar not found";
    private final CampingCarRepository campingCarRepository;
    private final CampingCarMapper campingCarMapper;

    @Override
    public CampingCarAdminResponseDto add(CampingCarRequestDto dto) {
        CampingCar campingCar = campingCarMapper.toEntity(dto);
        campingCar.validate();
        campingCar.evaluateRequiredDrivingLicence();
        CampingCar saved = campingCarRepository.save(campingCar);
        return campingCarMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampingCarAdminResponseDto> findByVehicleMetaDataActive(boolean active) {
        return campingCarRepository.findByVehicleMetaDataActive(active).stream()
                .map(campingCarMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampingCarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet) {
        return campingCarRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(campingCarMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampingCarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet) {
        return campingCarRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(campingCarMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampingCarAdminResponseDto> findAll() {
        return campingCarRepository.findAll().stream()
                .map(campingCarMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CampingCarAdminResponseDto getById(UUID id) {
        CampingCar campingCar = campingCarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CAMPING_CAR_NOT_FOUND));
        return campingCarMapper.toAdminResponseDto(campingCar);
    }

    @Override
    public void delete(UUID id) {
        campingCarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CAMPING_CAR_NOT_FOUND));
        // Check if Car can be deleted safely = no active rentals, if not, set vehicleMetaData field outOfFleet to true then persist.
        campingCarRepository.deleteById(id);
    }

    @Override
    public CampingCarAdminResponseDto patch(UUID id, CampingCarPatchRequestDto dto) {
        CampingCar old = campingCarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CAMPING_CAR_NOT_FOUND));
        CampingCar patchData = campingCarMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        old.evaluateRequiredDrivingLicence();
        CampingCar patched = campingCarRepository.save(old);
        return campingCarMapper.toAdminResponseDto(patched);
    }
}
