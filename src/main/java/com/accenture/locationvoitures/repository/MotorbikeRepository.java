package com.accenture.locationvoitures.repository;

import com.accenture.locationvoitures.model.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MotorbikeRepository extends JpaRepository<Motorbike, UUID> {
    List<Motorbike> findByVehicleMetaDataActive(boolean active);
    List<Motorbike> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);
    List<Motorbike> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(boolean active, boolean outOfFleet);
}
