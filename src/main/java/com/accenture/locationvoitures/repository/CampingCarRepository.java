package com.accenture.locationvoitures.repository;

import com.accenture.locationvoitures.model.CampingCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CampingCarRepository extends JpaRepository<CampingCar, UUID> {
    List<CampingCar> findByVehicleMetaDataActive(boolean active);
    List<CampingCar> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);
    List<CampingCar> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(boolean active,boolean outOfFleet);
}
