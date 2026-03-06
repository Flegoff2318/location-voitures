package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.model.Utility;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.repository.UtilityRepository;
import com.accenture.locationvoitures.service.UtilityService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.UtilityRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.UtilityPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.UtilityAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.UtilityMapper;
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
public class UtilityServiceImpl implements UtilityService {
    private final UtilityRepository utilityRepository;
    private final AdminRepository adminRepository;
    private final UtilityMapper utilityMapper;

    @Override
    public UtilityAdminResponseDto add(UtilityRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        Utility utility = utilityMapper.toEntity(dto);
        utility.validate();
        utility.evaluateRequiredDrivingLicence();
        Utility saved = utilityRepository.save(utility);
        return utilityMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilityAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return utilityRepository.findByVehicleMetaDataActive(active).stream()
                .map(utilityMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilityAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return utilityRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(utilityMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilityAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return utilityRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(utilityMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilityAdminResponseDto> findAll(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return utilityRepository.findAll().stream()
                .map(utilityMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UtilityAdminResponseDto getById(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        Utility utility = utilityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Utility not found"));
        return utilityMapper.toAdminResponseDto(utility);
    }

    @Override
    public void delete(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        utilityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Utility not found"));
        // Check if Utility can be deleted safely = no active rentals, if not, set vehicleMetaData field outOfFleet to true then persist.
        utilityRepository.deleteById(id);
    }

    @Override
    public UtilityAdminResponseDto patch(UUID id, UtilityPatchRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        Utility old = utilityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Utility not found"));
        Utility patchData = utilityMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        old.evaluateRequiredDrivingLicence();
        Utility patched = utilityRepository.save(old);
        return utilityMapper.toAdminResponseDto(patched);
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
