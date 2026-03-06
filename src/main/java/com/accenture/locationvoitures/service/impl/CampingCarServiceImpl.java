package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.model.CampingCar;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.repository.CampingCarRepository;
import com.accenture.locationvoitures.service.CampingCarService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.CampingCarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CampingCarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CampingCarAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.CampingCarMapper;
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
public class CampingCarServiceImpl implements CampingCarService {
    private final CampingCarRepository campingCarRepository;
    private final AdminRepository adminRepository;
    private final CampingCarMapper campingCarMapper;

    @Override
    public CampingCarAdminResponseDto add(CampingCarRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        CampingCar campingCar = campingCarMapper.toEntity(dto);
        campingCar.validate();
        campingCar.evaluateRequiredDrivingLicence();
        CampingCar saved = campingCarRepository.save(campingCar);
        return campingCarMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampingCarAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return campingCarRepository.findByVehicleMetaDataActive(active).stream()
                .map(campingCarMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampingCarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return campingCarRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(campingCarMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampingCarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return campingCarRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(campingCarMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampingCarAdminResponseDto> findAll(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return campingCarRepository.findAll().stream()
                .map(campingCarMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CampingCarAdminResponseDto getById(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        CampingCar campingCar = campingCarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        return campingCarMapper.toAdminResponseDto(campingCar);
    }

    @Override
    public void delete(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        campingCarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        // Check if Car can be deleted safely = no active rentals, if not, set vehicleMetaData field outOfFleet to true then persist.
        campingCarRepository.deleteById(id);
    }

    @Override
    public CampingCarAdminResponseDto patch(UUID id, CampingCarPatchRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        CampingCar old = campingCarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        CampingCar patchData = campingCarMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        old.evaluateRequiredDrivingLicence();
        CampingCar patched = campingCarRepository.save(old);
        return campingCarMapper.toAdminResponseDto(patched);
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
