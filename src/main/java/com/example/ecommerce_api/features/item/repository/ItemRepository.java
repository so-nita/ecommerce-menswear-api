package com.example.ecommerce_api.features.item.repository;

import com.example.ecommerce_api.features.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

}
