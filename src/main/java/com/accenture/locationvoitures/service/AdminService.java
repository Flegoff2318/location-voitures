package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.AdminPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.AdminResponseDto;

import java.util.UUID;

public interface AdminService {
    AdminResponseDto addAdmin(AdminRequestDto dto);

    AdminResponseDto getAdminDetailsById(UUID uuid, PersonRequestDto credentials);

    AdminResponseDto getAdminDetailsByCredentials(PersonRequestDto credentials);

    void deleteAdmin(PersonRequestDto credentials);

    AdminResponseDto patch(AdminPatchRequestDto dto, PersonRequestDto credentials);
}
