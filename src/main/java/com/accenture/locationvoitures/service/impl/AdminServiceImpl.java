package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.exception.CustomerException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.model.Customer;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.service.AdminService;
import com.accenture.locationvoitures.service.dto.request.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.AdminResponseDto;
import com.accenture.locationvoitures.service.mapper.AdminMapper;
import com.accenture.locationvoitures.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public AdminResponseDto addAdmin(AdminRequestDto dto) {
        Util.verifyAdmin(dto);
        Admin admin = adminMapper.toEntity(dto);
        Admin saved = adminRepository.save(admin);

        return adminMapper.toResponseDto(saved);
    }

    @Override
    public AdminResponseDto getAdminDetailsById(UUID uuid, PersonRequestDto dto) {
        Util.verifyPerson(dto);
        Optional<Admin> optAdmin = adminRepository.findById(uuid);
        if (optAdmin.isEmpty())
            throw new AdminException("Admin not found", HttpStatus.NOT_FOUND);
        Admin admin = optAdmin.get();
        if (!admin.getEmail().equals(dto.email()) || !admin.getPassword().equals(dto.password()))
            throw new AdminException("Access denied", HttpStatus.FORBIDDEN);
        return adminMapper.toResponseDto(admin);
    }

    @Override
    public AdminResponseDto getAdminDetailsByCredentials(PersonRequestDto dto) {
        Util.verifyPerson(dto);
        Admin admin = adminRepository.findByEmailAndPassword(dto.email(), dto.password());
        if (admin==null)
            throw new AdminException("Admin not found",HttpStatus.NOT_FOUND);
        if (!admin.getEmail().equals(dto.email()) || !admin.getPassword().equals(dto.password()))
            throw new AdminException("Access denied", HttpStatus.FORBIDDEN);
        return adminMapper.toResponseDto(admin);
    }

    @Override
    public void deleteAdmin(PersonRequestDto dto) {
        Util.verifyPerson(dto);
        Optional<Admin> optAdmin = Optional.ofNullable(adminRepository.findByEmailAndPassword(dto.email(), dto.password()));
        if (optAdmin.isEmpty())
            throw new AdminException("Admin not found", HttpStatus.NOT_FOUND);
        Admin admin = optAdmin.get();
        if (!admin.getEmail().equals(dto.email()) || !admin.getPassword().equals(dto.password()))
            throw new AdminException("Access denied", HttpStatus.FORBIDDEN);
        adminRepository.delete(admin);
    }

}
