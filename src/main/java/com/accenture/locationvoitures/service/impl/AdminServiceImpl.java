package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.AdminException;
import com.accenture.locationvoitures.exception.CustomerException;
import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.repository.AdminRepository;
import com.accenture.locationvoitures.service.AdminService;
import com.accenture.locationvoitures.service.dto.request.person.patch.AdminPatchRequestDto;
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
        if(adminRepository.findByEmail(admin.getEmail()).isPresent())
            throw new AdminException("email.used",HttpStatus.BAD_REQUEST);
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
            throw new EntityNotFoundException("admin.notfound");
        Admin admin = optAdmin.get();
        return adminMapper.toResponseDto(admin);
    }

    @Override
    public void deleteAdmin(String email) {
        long adminCount = adminRepository.count();
        if(adminCount==1){
            throw new AdminException("admin.delete.last",HttpStatus.BAD_REQUEST);
        }
        Admin admin = adminRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("admin.notfound"));
        adminRepository.delete(admin);
    }

    @Override
    public AdminResponseDto patch(String email,AdminPatchRequestDto dto) {
        if (dto == null)
            throw new AdminException("dto.null", HttpStatus.BAD_REQUEST);
        Optional<Admin> optAdmin = adminRepository.findByEmail(email);
        if (optAdmin.isEmpty())
            throw new EntityNotFoundException("admin.notfound");
        Admin admin = optAdmin.get();

        Admin patched = patchAdminData(dto, admin);
        return adminMapper.toResponseDto(patched);
    }

    private @NonNull Admin patchAdminData(AdminPatchRequestDto dto, Admin admin) {
        if (dto.firstname() != null) {
            if (dto.firstname().isBlank())
                throw new AdminException("person.firstname.blank", HttpStatus.BAD_REQUEST);
            admin.setFirstname(dto.firstname());
        }
        if (dto.lastname() != null) {
            if (dto.lastname().isBlank())
                throw new AdminException("person.lastname.blank", HttpStatus.BAD_REQUEST);
            admin.setLastname(dto.lastname());
        }
        if (dto.password() != null) {
            if (dto.password().isBlank())
                throw new AdminException("person.password.blank", HttpStatus.BAD_REQUEST);
            admin.setPassword(passwordEncoder.encode(dto.password()));
        }
        if(dto.companyFunction()!=null){
            if(dto.companyFunction().isBlank())
                throw new AdminException("admin.companyfunction.blank", HttpStatus.BAD_REQUEST);
            admin.setCompanyFunction(dto.companyFunction());
        }

        return adminRepository.save(admin);
    }

    private void verifyAdmin(AdminRequestDto dto) {
        if (dto == null)
            throw new AdminException("dto.null", HttpStatus.BAD_REQUEST);
        if (dto.firstname() == null)
            throw new CustomerException("person.firstname.null", HttpStatus.BAD_REQUEST);
        if(dto.firstname().isBlank())
            throw new CustomerException("person.firstname.blank", HttpStatus.BAD_REQUEST);
        if (dto.lastname() == null)
            throw new CustomerException("person.lastname.null", HttpStatus.BAD_REQUEST);
        if(dto.lastname().isBlank())
            throw new CustomerException("person.lastname.blank", HttpStatus.BAD_REQUEST);
        if (dto.email() == null)
            throw new CustomerException("person.email.null", HttpStatus.BAD_REQUEST);
        if(dto.email().isBlank())
            throw new CustomerException("person.email.blank", HttpStatus.BAD_REQUEST);
        if (dto.password() == null)
            throw new CustomerException("person.password.null", HttpStatus.BAD_REQUEST);
        if(dto.password().isBlank())
            throw new CustomerException("person.password.blank", HttpStatus.BAD_REQUEST);
        if (dto.companyFunction() == null)
            throw new AdminException("admin.companyfunction.null", HttpStatus.BAD_REQUEST);
        if(dto.companyFunction().isBlank())
            throw new AdminException("admin.companyfunction.blank", HttpStatus.BAD_REQUEST);
    }

}
