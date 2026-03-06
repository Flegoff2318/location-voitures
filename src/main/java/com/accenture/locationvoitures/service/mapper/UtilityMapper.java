package com.accenture.locationvoitures.service.mapper;

import com.accenture.locationvoitures.model.Utility;
import com.accenture.locationvoitures.service.dto.request.vehicle.UtilityRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.UtilityPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.UtilityAdminResponseDto;
import com.accenture.locationvoitures.service.dto.response.customer.vehicle.UtilityCustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VehicleMetaDataMapper.class})
public interface UtilityMapper {
    Utility toEntity(UtilityRequestDto dto);
    Utility toEntity(UtilityPatchRequestDto dto);

    UtilityAdminResponseDto toAdminResponseDto(Utility utility);
    UtilityCustomerResponseDto toCustomerResponseDto(Utility utility);
}
