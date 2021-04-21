package com.timtro247.maven1311.repository;

import com.timtro247.maven1311.model.security.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
    Optional<Roles> findById(Long id);
}
