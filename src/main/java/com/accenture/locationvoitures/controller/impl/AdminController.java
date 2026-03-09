package com.accenture.locationvoitures.controller.impl;

import com.accenture.locationvoitures.controller.AdminApi;
import com.accenture.locationvoitures.service.AdminService;
import com.accenture.locationvoitures.service.dto.request.person.AdminPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.person.AdminResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@AllArgsConstructor
public class AdminController implements AdminApi {
    private final AdminService adminService;

    @Override
    public ResponseEntity<Void> create(@Valid AdminRequestDto dto) {
        AdminResponseDto responseDto = adminService.addAdmin(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.uuid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<AdminResponseDto> readAccountDetails(@RequestHeader(name = "authorization") String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(adminService.getAdminDetailsByEmail(credentials.email()));
    }

    @Override
    public ResponseEntity<AdminResponseDto> delete(@RequestHeader(name = "authorization") String base64Header) {
        PersonRequestDto credentials = getCredentials(base64Header);
        adminService.deleteAdmin(credentials.email());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<AdminResponseDto> patch(@RequestHeader(name = "authorization") String base64Header,AdminPatchRequestDto dto) {
        PersonRequestDto credentials = getCredentials(base64Header);
        return ResponseEntity.ok(adminService.patch(credentials.email(), dto));
    }

    private @NonNull PersonRequestDto getCredentials(String base64Header) {
        byte[] decoded = Base64.getDecoder().decode(base64Header.split(" ")[1]);
        String[] content = new String(decoded, StandardCharsets.UTF_8).split(":");
        return new PersonRequestDto(content[0], content[1]);
    }

}
