package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.model.Bike;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.repository.BikeRepository;
import com.accenture.locationvoitures.service.BikeService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.BikeMapper;
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
public class BikeServiceImpl implements BikeService {
    private final BikeRepository bikeRepository;
    private final AdminRepository adminRepository;
    private final BikeMapper bikeMapper;

    @Override
    public BikeAdminResponseDto add(BikeRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        Bike bike = bikeMapper.toEntity(dto);
        bike.validate();
        bike.setRequiredDrivingLicence(EDrivingLicence.NONE);
        Bike saved = bikeRepository.save(bike);
        return bikeMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BikeAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        return bikeRepository.findByVehicleMetaDataActive(active).stream()
                .map(bikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BikeAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        return bikeRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(bikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BikeAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        return bikeRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(bikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BikeAdminResponseDto> findAll(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return bikeRepository.findAll().stream()
                .map(bikeMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BikeAdminResponseDto getById(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        Bike bike = bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bike not found"));
        return bikeMapper.toAdminResponseDto(bike);
    }

    @Override
    public void delete(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bike not found"));

        bikeRepository.deleteById(id);
    }

    @Override
    public BikeAdminResponseDto patch(UUID id, BikePatchRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        Bike old = bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bike not found"));
        Bike patchData = bikeMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        Bike patched = bikeRepository.save(old);
        return bikeMapper.toAdminResponseDto(patched);
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
