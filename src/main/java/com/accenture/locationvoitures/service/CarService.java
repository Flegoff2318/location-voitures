package com.accenture.locationvoitures.service;

import com.accenture.locationvoitures.service.dto.request.CarRequestDto;
import com.accenture.locationvoitures.service.dto.request.PersonRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.CarAdminResponseDto;

public interface CarService {
    CarAdminResponseDto add(CarRequestDto dto, PersonRequestDto credentials);
}
