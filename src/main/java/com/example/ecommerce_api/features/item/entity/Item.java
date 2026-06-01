package com.example.ecommerce_api.features.item.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.constant.ForeignKeyConstant;
import com.example.ecommerce_api.contract.BaseEntity;
import com.example.ecommerce_api.features.category.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = EntityConstant.ITEM)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String name;

    @Column()
    private Number price;

    @Column()
    private String imageId;

    @Column(nullable = true)
    private String description;

    // Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ForeignKeyConstant.CATEGORY_ID)
    private Category category;
}

