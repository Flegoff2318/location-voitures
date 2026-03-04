package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Admin;
import com.accenture.locationvoitures.service.dto.request.person.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.person.AdminResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toEntity(AdminRequestDto dto);

    AdminResponseDto toResponseDto(Admin entity);
}
