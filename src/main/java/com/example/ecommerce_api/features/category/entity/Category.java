package com.example.ecommerce_api.features.category.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.features.item.entity.Item;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = EntityConstant.CATEGORY)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
}
