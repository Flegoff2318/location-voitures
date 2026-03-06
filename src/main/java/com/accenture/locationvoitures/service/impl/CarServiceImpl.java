package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.model.Car;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.repository.CarRepository;
import com.accenture.locationvoitures.service.CarService;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CarAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.CarMapper;
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
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final AdminRepository adminRepository;
    private final CarMapper carMapper;

    @Override
    public CarAdminResponseDto add(CarRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        Car car = carMapper.toEntity(dto);
        car.validate();
        car.evaluateRequiredDrivingLicence();
        Car saved = carRepository.save(car);
        return carMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return carRepository.findByVehicleMetaDataActive(active).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return carRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return carRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarAdminResponseDto> findAll(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return carRepository.findAll().stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CarAdminResponseDto getById(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        return carMapper.toAdminResponseDto(car);
    }

    @Override
    public void delete(UUID id, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        // Check if Car can be deleted safely = no active rentals, if not, set vehicleMetaData field outOfFleet to true then persist.
        carRepository.deleteById(id);
    }

    @Override
    public CarAdminResponseDto patch(UUID id, CarPatchRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);

        Car old = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        Car patchData = carMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        old.evaluateRequiredDrivingLicence();
        Car patched = carRepository.save(old);
        return carMapper.toAdminResponseDto(patched);
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
