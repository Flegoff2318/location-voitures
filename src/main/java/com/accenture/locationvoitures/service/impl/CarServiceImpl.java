package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.model.Car;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.repository.CarRepository;
import com.accenture.locationvoitures.service.CarService;
import com.accenture.locationvoitures.service.dto.request.vehicle.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CarAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.CarMapper;
import com.accenture.locationvoitures.service.util.Util;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final AdminRepository adminRepository;
    private final CarMapper carMapper;

    @Override
    public CarAdminResponseDto add(CarRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        dto.verifyData();
        Car car = carMapper.toEntity(dto);
        car.evaluateRequiredDrivingLicence();
        Car saved = carRepository.save(car);
        return carMapper.toAdminResponseDto(saved);
    }

    @Override
    public List<CarAdminResponseDto> findByVehicleMetaDataActive(boolean active, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return carRepository.findByVehicleMetaDataActive(active).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    public List<CarAdminResponseDto> findByVehicleMetaDataOutOfFleet(boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return carRepository.findByVehicleMetaDataOutOfFleet(outOfFleet).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    public List<CarAdminResponseDto> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(Boolean active, Boolean outOfFleet, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return carRepository.findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(active, outOfFleet).stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
    public List<CarAdminResponseDto> findAll(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        return carRepository.findAll().stream()
                .map(carMapper::toAdminResponseDto)
                .toList();
    }

    @Override
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
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        // Check if Car can be deleted safely, if not, set vehicleMetaData field outOfFleet to true then persist.
        try{
            carRepository.deleteById(id);
        } catch (Exception _) {
            car.getVehicleMetaData().setOutOfFleet(true);
            carRepository.save(car);
        }
    }

    @Override
    public CarAdminResponseDto patch(UUID id, CarPatchRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin _ = getAdmin(credentials);
        dto.verifyData();
        Car old = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        Car patchedCarData = patchFields(old,dto);
        Car patched = carRepository.save(patchedCarData);
        return carMapper.toAdminResponseDto(patched);
    }

    private Car patchFields(Car car, CarPatchRequestDto dto) {
        Car newData = carMapper.toEntity(dto);
        patchAllCarFields(car, newData);
        return car;
    }

    private void patchAllCarFields(Car car, Car newData) {
        patchCarFields(car, newData);
        patchFourWheeledFields(car, newData);
        patchVehicleFields(car, newData);
    }

    private void patchVehicleFields(Car car, Car newData) {
        if(newData.getBrand()!=null)
            car.setBrand(newData.getBrand());
        if(newData.getModel()!=null)
            car.setModel(newData.getModel());
        if(newData.getColor()!=null)
            car.setColor(newData.getColor());
        patchVehicleMetaDataFields(car, newData);
    }

    private void patchVehicleMetaDataFields(Car car, Car newData) {
        if(newData.getVehicleMetaData()!=null){
            if(newData.getVehicleMetaData().getDailyRentalPrice()!=null)
                car.getVehicleMetaData().setDailyRentalPrice(newData.getVehicleMetaData().getDailyRentalPrice());
            if(newData.getVehicleMetaData().getMileage()!=null)
                car.getVehicleMetaData().setMileage(newData.getVehicleMetaData().getMileage());
            if(newData.getVehicleMetaData().getActive()!=null)
                car.getVehicleMetaData().setActive(newData.getVehicleMetaData().getActive());
            if(newData.getVehicleMetaData().getOutOfFleet()!=null)
                car.getVehicleMetaData().setOutOfFleet(newData.getVehicleMetaData().getOutOfFleet());
        }
    }

    private void patchFourWheeledFields(Car car, Car newData) {
        if(newData.getNumberOfSeats()!=null){
            car.setNumberOfSeats(newData.getNumberOfSeats());
            if(car.getNumberOfSeats()<=9)
                car.setRequiredDrivingLicence(EDrivingLicence.B);
            if(car.getNumberOfSeats()>=10 && car.getNumberOfSeats()<=16)
                car.setRequiredDrivingLicence(EDrivingLicence.D1);
        }
        if(newData.getFuelType()!=null)
            car.setFuelType(newData.getFuelType());
        if(newData.getTransmission()!=null)
            car.setTransmission(newData.getTransmission());
        if(newData.getAirConditioning()!=null)
            car.setAirConditioning(newData.getAirConditioning());
    }

    private void patchCarFields(Car car, Car newData) {
        if(newData.getCarType()!=null)
            car.setCarType(newData.getCarType());
        if(newData.getNumberOfLuggage()!=null)
            car.setNumberOfLuggage(newData.getNumberOfLuggage());
        if(newData.getNumberOfDoors()!=null)
            car.setNumberOfDoors(newData.getNumberOfDoors());
    }

    private @NonNull Admin getAdmin(PersonRequestDto credentials) {
        Optional<Admin> optAdmin = Optional.ofNullable(adminRepository.findByEmailAndPassword(credentials.email(), credentials.password()));
        if (optAdmin.isEmpty())
            throw new AdminException("Admin not found", HttpStatus.NOT_FOUND);
        Admin admin = optAdmin.get();

        if (!admin.getEmail().equals(credentials.email()) || !admin.getPassword().equals(credentials.password()))
            throw new AdminException("Access denied", HttpStatus.FORBIDDEN);
        return admin;
    }
}
