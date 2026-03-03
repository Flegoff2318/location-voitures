package com.accenture.locationvoitures.repository;

import com.accenture.locationvoitures.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

}
