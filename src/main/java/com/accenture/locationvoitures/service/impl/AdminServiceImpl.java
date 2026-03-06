package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.service.AdminService;
import com.accenture.locationvoitures.service.dto.request.person.AdminPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.person.AdminResponseDto;
import com.accenture.locationvoitures.service.mapper.AdminMapper;
import com.accenture.locationvoitures.service.util.Util;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
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
    @Transactional(readOnly = true)
    public AdminResponseDto getAdminDetailsById(UUID uuid, PersonRequestDto dto) {
        Util.verifyPerson(dto);
        Optional<Admin> optAdmin = adminRepository.findById(uuid);
        if (optAdmin.isEmpty())
            throw new AdminException("Admin not found", HttpStatus.NOT_FOUND);
        Admin admin = optAdmin.get();
        if (!admin.getEmail().equals(dto.email()) || !admin.getPassword().equals(dto.password()))
            throw new AdminException("Access forbidden", HttpStatus.FORBIDDEN);
        return adminMapper.toResponseDto(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminResponseDto getAdminDetailsByCredentials(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Admin admin = adminRepository.findByEmailAndPassword(credentials.email(), credentials.password());
        if (admin == null)
            throw new AdminException("Admin not found", HttpStatus.NOT_FOUND);
        if (!admin.getEmail().equals(credentials.email()) || !admin.getPassword().equals(credentials.password()))
            throw new AdminException("Access forbidden", HttpStatus.FORBIDDEN);
        return adminMapper.toResponseDto(admin);
    }

    @Override
    public void deleteAdmin(PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        Optional<Admin> optAdmin = Optional.ofNullable(adminRepository.findByEmailAndPassword(credentials.email(), credentials.password()));
        if (optAdmin.isEmpty())
            throw new AdminException("Admin not found", HttpStatus.NOT_FOUND);
        Admin admin = optAdmin.get();
        if (!admin.getEmail().equals(credentials.email()) || !admin.getPassword().equals(credentials.password()))
            throw new AdminException("Access forbidden", HttpStatus.FORBIDDEN);
        adminRepository.delete(admin);
    }

    @Override
    public AdminResponseDto patch(AdminPatchRequestDto dto, PersonRequestDto credentials) {
        Util.verifyPerson(credentials);
        if (dto == null)
            throw new AdminException("DTO is null", HttpStatus.BAD_REQUEST);
        Admin admin = getAdmin(credentials);

        Admin patched = patchAdminData(dto, admin);
        return adminMapper.toResponseDto(patched);
    }

    private @NonNull Admin patchAdminData(AdminPatchRequestDto dto, Admin admin) {
        if (dto.firstname() != null) {
            if (dto.firstname().isBlank())
                throw new AdminException("Firstname can't be blank", HttpStatus.BAD_REQUEST);
            admin.setFirstname(dto.firstname());
        }
        if (dto.lastname() != null) {
            if (dto.lastname().isBlank())
                throw new AdminException("Lastname can't be blank", HttpStatus.BAD_REQUEST);
            admin.setLastname(dto.lastname());
        }
        if (dto.password() != null) {
            if (dto.password().isBlank())
                throw new AdminException("Password can't be blank", HttpStatus.BAD_REQUEST);
            admin.setPassword(dto.password());
        }
        if(dto.companyFunction()!=null){
            if(dto.companyFunction().isBlank())
                throw new AdminException("Company Function can't be blank", HttpStatus.BAD_REQUEST);
            admin.setCompanyFunction(dto.companyFunction());
        }

        return adminRepository.save(admin);
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
