package com.accenture.locationvoitures.fake;

import com.accenture.locationvoitures.model.Bike;
import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;
import com.accenture.locationvoitures.service.dto.response.customer.vehicle.BikeCustomerResponseDto;
import com.accenture.locationvoitures.service.mapper.BikeMapper;

import java.util.UUID;

public class FakeBikeMapper implements BikeMapper {
    @Override
    public Bike toEntity(BikeRequestDto dto) {
        FakeVehicleMetaDataMapper metaDataMapper = new FakeVehicleMetaDataMapper();
        Bike bike = new Bike();
        bike.setUuid(UUID.randomUUID());
        bike.setBrand(dto.getBrand());
        bike.setModel(dto.getModel());
        bike.setColor(dto.getColor());
        bike.setVehicleMetaData(metaDataMapper.toEntity(dto.getVehicleMetaData()));

        bike.setWeight(dto.getWeight());
        bike.setFrameSize(dto.getFrameSize());
        bike.setDiscBrakes(dto.getDiscBrakes());
        bike.setIsElectric(dto.getIsElectric());
        bike.setBatteryCapacity(dto.getBatteryCapacity());
        bike.setAutonomy(dto.getAutonomy());
        bike.setBikeType(dto.getBikeType());

        return bike;
    }

    @Override
    public Bike toEntity(BikePatchRequestDto dto) {
        FakeVehicleMetaDataMapper metaDataMapper = new FakeVehicleMetaDataMapper();
        Bike bike = new Bike();
        bike.setBrand(dto.getBrand());
        bike.setModel(dto.getModel());
        bike.setColor(dto.getColor());
        if (dto.getVehicleMetaData() != null) {
            bike.setVehicleMetaData(metaDataMapper.toEntity(dto.getVehicleMetaData()));
        }else{
            bike.setVehicleMetaData(null);
        }

        bike.setWeight(dto.getWeight());
        bike.setFrameSize(dto.getFrameSize());
        bike.setDiscBrakes(dto.getDiscBrakes());
        bike.setIsElectric(dto.getIsElectric());
        bike.setBatteryCapacity(dto.getBatteryCapacity());
        bike.setAutonomy(dto.getAutonomy());
        bike.setBikeType(dto.getBikeType());

        return bike;
    }

    @Override
    public BikeAdminResponseDto toAdminResponseDto(Bike bike) {
        FakeVehicleMetaDataMapper metaDataMapper = new FakeVehicleMetaDataMapper();
        return new BikeAdminResponseDto(
                bike.getUuid(),
                bike.getBrand(),
                bike.getModel(),
                bike.getColor(),
                bike.getRequiredDrivingLicence(),
                metaDataMapper.toResponseDto(bike.getVehicleMetaData()),
                bike.getWeight(),
                bike.getFrameSize(),
                bike.getDiscBrakes(),
                bike.getIsElectric(),
                bike.getBatteryCapacity(),
                bike.getAutonomy(),
                bike.getBikeType()
        );
    }

    @Override
    public BikeCustomerResponseDto toCustomerResponseDto(Bike bike) {
        return null;
    }
}
