package com.accenture.locationvoitures.repository;

import com.accenture.locationvoitures.model.Utility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UtilityRepository extends JpaRepository<Utility, UUID> {
    List<Utility> findByVehicleMetaDataActive(boolean active);
    List<Utility> findByVehicleMetaDataOutOfFleet(boolean outOfFleet);
    List<Utility> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(boolean active,boolean outOfFleet);
}
