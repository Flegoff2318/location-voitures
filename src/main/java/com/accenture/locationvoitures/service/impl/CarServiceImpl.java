package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.model.Car;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.repository.CarRepository;
import com.accenture.locationvoitures.service.CarService;
import com.accenture.locationvoitures.service.dto.request.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.CarAdminResponseDto;
import com.accenture.locationvoitures.service.mapper.CarMapper;
import com.accenture.locationvoitures.service.util.Util;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
