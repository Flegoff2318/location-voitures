package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.service.AdminService;
import com.accenture.locationvoitures.service.dto.request.person.AdminPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.person.AdminResponseDto;
import com.accenture.locationvoitures.service.mapper.AdminMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto addAdmin(AdminRequestDto dto) {
        verifyAdmin(dto);
        Admin admin = adminMapper.toEntity(dto);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole("ROLE_ADMIN");
        Admin saved = adminRepository.save(admin);

        return adminMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminResponseDto getAdminDetailsByEmail(String email) {
        Optional<Admin> optAdmin = adminRepository.findByEmail(email);
        if (optAdmin.isEmpty())
            throw new EntityNotFoundException("User not found");
        Admin admin = optAdmin.get();
        return adminMapper.toResponseDto(admin);
    }

    @Override
    public void deleteAdmin(String email) {
        long adminCount = adminRepository.count();
        if(adminCount==1){
            throw new AdminException("You can't delete the last admin",HttpStatus.BAD_REQUEST);
        }
        Admin admin = adminRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("User not found"));
        adminRepository.delete(admin);
    }

    @Override
    public AdminResponseDto patch(String email,AdminPatchRequestDto dto) {
        if (dto == null)
            throw new AdminException("DTO is null", HttpStatus.BAD_REQUEST);
        Optional<Admin> optAdmin = adminRepository.findByEmail(email);
        if (optAdmin.isEmpty())
            throw new EntityNotFoundException("User not found");
        Admin admin = optAdmin.get();

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

    private void verifyAdmin(AdminRequestDto dto) {
        if (dto == null)
            throw new AdminException("DTO is null", HttpStatus.BAD_REQUEST);
        if (dto.firstname() == null || dto.firstname().isBlank())
            throw new AdminException("Firstname is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.lastname() == null || dto.lastname().isBlank())
            throw new AdminException("Lastname is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.email() == null || dto.email().isBlank())
            throw new AdminException("Email is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.password() == null || dto.password().isBlank())
            throw new AdminException("Password is null or blank", HttpStatus.BAD_REQUEST);
        if (dto.companyFunction() == null || dto.companyFunction().isBlank())
            throw new AdminException("CompanyFunction is null or blank", HttpStatus.BAD_REQUEST);
    }

}
