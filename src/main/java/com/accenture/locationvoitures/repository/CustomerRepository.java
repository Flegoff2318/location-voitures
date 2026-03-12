package com.accenture.locationvoitures.repository;

import com.accenture.locationvoitures.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query("""
            SELECT c FROM Customer c
            LEFT JOIN FETCH c.address
            WHERE c.email = :email
            """)
    Optional<Customer> findByEmail(String email);
}
