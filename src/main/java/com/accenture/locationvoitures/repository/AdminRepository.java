package com.accenture.locationvoitures.repository;

import com.accenture.locationvoitures.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    @Query("""
            SELECT DISTINCT a FROM Admin a
            WHERE a.email=:email AND password=:password
            """)
    Admin findByEmailAndPassword(String email, String password);
}
