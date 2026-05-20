package com.example.ecommerce_api.features.address.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.constant.ForeignKeyConstant;
import com.example.ecommerce_api.features.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = EntityConstant.ADDRESS)
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = ForeignKeyConstant.FK_USER_ID, nullable = false)
    private User user;
}
