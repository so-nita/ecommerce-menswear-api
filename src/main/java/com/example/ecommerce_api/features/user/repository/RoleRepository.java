package com.example.ecommerce_api.features.user.repository;

import com.example.ecommerce_api.features.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findFirstByName(String name);
}
