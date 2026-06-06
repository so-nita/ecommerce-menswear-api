package com.example.ecommerce_api.features.user.repository;

import java.util.Optional;

import com.example.ecommerce_api.features.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUsernameOrPhoneNumber(String username, String phoneNumber);

    // fetches role eagerly so it can be read outside the persistence session (e.g. JWT filter)
    @EntityGraph(attributePaths = {"role"})
    Optional<User> findWithRoleById(String id);
//    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
//    boolean existsByEmail(String email);
}
