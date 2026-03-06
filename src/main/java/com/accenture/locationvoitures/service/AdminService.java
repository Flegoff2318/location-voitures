package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.person.AdminPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.person.AdminResponseDto;

import java.util.UUID;

public interface AdminService {
    AdminResponseDto addAdmin(AdminRequestDto dto);

    AdminResponseDto getAdminDetailsById(UUID uuid, PersonRequestDto credentials);

    AdminResponseDto getAdminDetailsByCredentials(PersonRequestDto credentials);

    void deleteAdmin(PersonRequestDto credentials);

    AdminResponseDto patch(AdminPatchRequestDto dto, PersonRequestDto credentials);
}
