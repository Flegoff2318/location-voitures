package com.accenture.locationvoitures.fake;

import com.accenture.locationvoitures.model.VehicleMetaData;
import com.accenture.locationvoitures.service.dto.request.vehicle.VehicleMetaDataRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.VehicleMetaDataPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.VehicleMetaDataResponseDto;
import com.accenture.locationvoitures.service.mapper.VehicleMetaDataMapper;
import jakarta.validation.Valid;

import java.util.concurrent.atomic.AtomicLong;

public class FakeVehicleMetaDataMapper implements VehicleMetaDataMapper {
    private final AtomicLong seq = new AtomicLong(1);

    @Override
    public VehicleMetaData toEntity(VehicleMetaDataRequestDto dto) {
        VehicleMetaData vmd = new VehicleMetaData();
        vmd.setId(seq.getAndIncrement());
        vmd.setDailyRentalPrice(dto.dailyRentalPrice());
        vmd.setMileage(dto.mileage());
        vmd.setActive(dto.active());
        vmd.setOutOfFleet(dto.outOfFleet());
        return vmd;
    }

    @Override
    public VehicleMetaDataResponseDto toResponseDto(VehicleMetaData entity) {
        return new VehicleMetaDataResponseDto(
                entity.getDailyRentalPrice(),
                entity.getMileage(),
                entity.getActive(),
                entity.getOutOfFleet()
        );
    }

    public VehicleMetaData toEntity(@Valid VehicleMetaDataPatchRequestDto dto) {
        VehicleMetaData vmd = new VehicleMetaData();
        vmd.setDailyRentalPrice(dto.dailyRentalPrice());
        vmd.setMileage(dto.mileage());
        vmd.setActive(dto.active());
        vmd.setOutOfFleet(dto.outOfFleet());
        return vmd;
    }
}
