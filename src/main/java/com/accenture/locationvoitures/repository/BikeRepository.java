package com.accenture.locationvoitures.repository;

import com.accenture.locationvoitures.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BikeRepository extends JpaRepository<Bike, UUID> {
    List<Bike> findByVehicleMetaDataActive(boolean active);

    List<Bike> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);

    List<Bike> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(boolean active, boolean outOfFleet);
}
