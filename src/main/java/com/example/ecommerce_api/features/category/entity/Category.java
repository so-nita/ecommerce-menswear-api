package com.example.ecommerce_api.features.category.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.constant.MapByConstant;
import com.example.ecommerce_api.contract.BaseEntity;
import com.example.ecommerce_api.features.item.entity.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = EntityConstant.CATEGORY)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column(nullable = true)
    private String description;

    private String imageId;

    @OneToMany(mappedBy = MapByConstant.CATEGORY, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
}
