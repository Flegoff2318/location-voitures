package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.model.Utility;
import com.accenture.locationvoitures.repository.UtilityRepository;
import com.accenture.locationvoitures.service.UtilityService;
import com.accenture.locationvoitures.service.dto.request.vehicle.UtilityRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.UtilityPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.UtilityAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.UtilityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class UtilityServiceImpl implements UtilityService {
    private static final String UTILITY_NOT_FOUND = "Utility not found";
    private final UtilityRepository utilityRepository;
    private final UtilityMapper utilityMapper;

    @Override
    public UtilityAdminResponseDto add(UtilityRequestDto dto) {
        Utility utility = utilityMapper.toEntity(dto);
        utility.validate();
        utility.evaluateRequiredDrivingLicence();
        Utility saved = utilityRepository.save(utility);
        return utilityMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilityAdminResponseDto> findByVehicleMetaDataActive(boolean active) {
        return utilityRepository.findByVehicleMetaDataActive(active).stream()
                .map(utilityMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilityAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet) {
        return utilityRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(utilityMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilityAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet) {
        return utilityRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(utilityMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilityAdminResponseDto> findAll() {
        return utilityRepository.findAll().stream()
                .map(utilityMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UtilityAdminResponseDto getById(UUID id) {
        Utility utility = utilityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(UTILITY_NOT_FOUND));
        return utilityMapper.toAdminResponseDto(utility);
    }

    @Override
    public void delete(UUID id) {
        utilityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(UTILITY_NOT_FOUND));
        // Check if Utility can be deleted safely = no active rentals, if not, set vehicleMetaData field outOfFleet to true then persist.
        utilityRepository.deleteById(id);
    }

    @Override
    public UtilityAdminResponseDto patch(UUID id, UtilityPatchRequestDto dto) {
        Utility old = utilityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(UTILITY_NOT_FOUND));
        Utility patchData = utilityMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        old.evaluateRequiredDrivingLicence();
        Utility patched = utilityRepository.save(old);
        return utilityMapper.toAdminResponseDto(patched);
    }
}
