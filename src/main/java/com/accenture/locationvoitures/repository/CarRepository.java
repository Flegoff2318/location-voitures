package com.accenture.locationvoitures.repository;

import com.accenture.locationvoitures.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByVehicleMetaDataActive(boolean active);
    List<Car> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);
    List<Car> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(boolean active,boolean outOfFleet);
}
