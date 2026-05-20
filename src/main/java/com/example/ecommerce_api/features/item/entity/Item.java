package com.example.ecommerce_api.features.item.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.features.category.entity.Category;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = EntityConstant.ITEM)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String name;

    @Column()
    private Number price;

    @Column()
    private String image;

    @Column(nullable = true)
    private String description;

    // Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}

