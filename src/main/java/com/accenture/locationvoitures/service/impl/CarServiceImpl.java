package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.model.Car;
import com.accenture.locationvoitures.repository.CarRepository;
import com.accenture.locationvoitures.service.CarService;
import com.accenture.locationvoitures.service.dto.request.vehicle.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CarAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.CarMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {
    private static final String CAR_NOT_FOUND = "Car not found";
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarAdminResponseDto add(CarRequestDto dto) {

        Car car = carMapper.toEntity(dto);
        car.validate();
        car.evaluateRequiredDrivingLicence();
        Car saved = carRepository.save(car);
        return carMapper.toAdminResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarAdminResponseDto> findByVehicleMetaDataActive(boolean active) {
        return carRepository.findByVehicleMetaDataActive(active).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet) {
        return carRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet) {

        return carRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarAdminResponseDto> findAll() {
        return carRepository.findAll().stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CarAdminResponseDto getById(UUID id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND));
        return carMapper.toAdminResponseDto(car);
    }

    @Override
    public void delete(UUID id) {
        carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND));
        // Check if Car can be deleted safely = no active rentals, if not, set vehicleMetaData field outOfFleet to true then persist.
        carRepository.deleteById(id);
    }

    @Override
    public CarAdminResponseDto patch(UUID id, CarPatchRequestDto dto) {

        Car old = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND));
        Car patchData = carMapper.toEntity(dto);
        patchData.checkUpdateData();
        old.applyPatch(patchData);
        old.evaluateRequiredDrivingLicence();
        Car patched = carRepository.save(old);
        return carMapper.toAdminResponseDto(patched);
    }

}
