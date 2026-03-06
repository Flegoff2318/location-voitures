package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.model.Motorbike;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.repository.MotorbikeRepository;
import com.accenture.locationvoitures.service.MotorbikeService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.MotorbikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.MotorbikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.MotorbikeAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.MotorbikeMapper;
import com.accenture.locationvoitures.service.util.Util;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class MotorbikeServiceImpl implements MotorbikeService {
    private final MotorbikeRepository motorbikeRepository;
    private final AdminRepository adminRepository;
    private final MotorbikeMapper motorbikeMapper;
    @Override
    public MotorbikeAdminResponseDto add(MotorbikeRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        Motorbike motorbike = motorbikeMapper.toEntity(dto);
        motorbike.validate();
        motorbike.evaluateRequiredDrivingLicence();
        Motorbike saved = motorbikeRepository.save(motorbike);
        return motorbikeMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MotorbikeAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return motorbikeRepository.findByVehicleMetaDataActive(active).stream()
                .map(motorbikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MotorbikeAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return motorbikeRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(motorbikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MotorbikeAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return motorbikeRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(motorbikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MotorbikeAdminResponseDto> findAll(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return motorbikeRepository.findAll().stream()
                .map(motorbikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MotorbikeAdminResponseDto getById(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        Motorbike motorbike = motorbikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Motorbike not found"));
        return motorbikeMapper.toAdminResponseDto(motorbike);
    }

    @Override
    public void delete(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        motorbikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Motorbike not found"));
        // Check if Motorbike can be deleted safely = no active rentals, if not, set vehicleMetaData field outOfFleet to true then persist.
        motorbikeRepository.deleteById(id);
    }

    @Override
    public MotorbikeAdminResponseDto patch(UUID id, MotorbikePatchRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        Motorbike old = motorbikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Motorbike not found"));
        Motorbike patchData = motorbikeMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        old.evaluateRequiredDrivingLicence();
        Motorbike patched = motorbikeRepository.save(old);
        return motorbikeMapper.toAdminResponseDto(patched);
    }

    private @NonNull Admin getAdmin(PersonRequestDto credentials) {
        Optional<Admin> optAdmin = Optional.ofNullable(adminRepository.findByEmailAndPassword(credentials.email(), credentials.password()));
        if (optAdmin.isEmpty())
            throw new AdminException("Admin not found", HttpStatus.NOT_FOUND);
        Admin admin = optAdmin.get();

        if (!admin.getEmail().equals(credentials.email()) || !admin.getPassword().equals(credentials.password()))
            throw new AdminException("Access forbidden", HttpStatus.FORBIDDEN);
        return admin;
    }
}
