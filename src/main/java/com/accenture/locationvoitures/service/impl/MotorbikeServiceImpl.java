package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.model.Motorbike;
import com.accenture.locationvoitures.repository.MotorbikeRepository;
import com.accenture.locationvoitures.service.MotorbikeService;
import com.accenture.locationvoitures.service.dto.request.vehicle.MotorbikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.MotorbikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.MotorbikeAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.MotorbikeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class MotorbikeServiceImpl implements MotorbikeService {
    private static final String MOTORBIKE_NOT_FOUND = "Motorbike not found";
    private final MotorbikeRepository motorbikeRepository;
    private final MotorbikeMapper motorbikeMapper;

    @Override
    public MotorbikeAdminResponseDto add(MotorbikeRequestDto dto) {
        Motorbike motorbike = motorbikeMapper.toEntity(dto);
        motorbike.validate();
        motorbike.evaluateRequiredDrivingLicence();
        Motorbike saved = motorbikeRepository.save(motorbike);
        return motorbikeMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MotorbikeAdminResponseDto> findByVehicleMetaDataActive(boolean active) {
        return motorbikeRepository.findByVehicleMetaDataActive(active).stream()
                .map(motorbikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MotorbikeAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet) {
        return motorbikeRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(motorbikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MotorbikeAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet) {
        return motorbikeRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(motorbikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MotorbikeAdminResponseDto> findAll() {
        return motorbikeRepository.findAll().stream()
                .map(motorbikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MotorbikeAdminResponseDto getById(UUID id) {
        Motorbike motorbike = motorbikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MOTORBIKE_NOT_FOUND));
        return motorbikeMapper.toAdminResponseDto(motorbike);
    }

    @Override
    public void delete(UUID id) {
        motorbikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MOTORBIKE_NOT_FOUND));
        // Check if Motorbike can be deleted safely = no active rentals, if not, set vehicleMetaData field outOfFleet to true then persist.
        motorbikeRepository.deleteById(id);
    }

    @Override
    public MotorbikeAdminResponseDto patch(UUID id, MotorbikePatchRequestDto dto) {

        Motorbike old = motorbikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MOTORBIKE_NOT_FOUND));
        Motorbike patchData = motorbikeMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        old.evaluateRequiredDrivingLicence();
        Motorbike patched = motorbikeRepository.save(old);
        return motorbikeMapper.toAdminResponseDto(patched);
    }
}
