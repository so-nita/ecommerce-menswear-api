package com.example.ecommerce_api.features.user.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.ecommerce_api.features.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
