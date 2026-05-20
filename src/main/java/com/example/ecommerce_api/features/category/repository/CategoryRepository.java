package com.example.ecommerce_api.features.category.repository;

import com.example.ecommerce_api.features.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
