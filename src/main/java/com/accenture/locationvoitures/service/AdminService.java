package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.person.patch.AdminPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.person.AdminResponseDto;

public interface AdminService {
    AdminResponseDto addAdmin(AdminRequestDto dto);

    AdminResponseDto getAdminDetailsByEmail(String email);

    void deleteAdmin(String email);

    AdminResponseDto patch(String email,AdminPatchRequestDto dto);
}
