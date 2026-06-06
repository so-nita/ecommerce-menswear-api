package com.example.ecommerce_api.features.user.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.constant.MapByConstant;
import com.example.ecommerce_api.contract.AuditEntity;
import com.example.ecommerce_api.features.address.entity.Address;
import com.example.ecommerce_api.features.file.entity.File;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = EntityConstant.USER)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AuditEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    private String username;

    @Column(nullable = false, unique = true, length = 50)
    private String phoneNumber;

    @Column(nullable = false)
    private String passwordHash;

    @Column()
    @Builder.Default
    private boolean phoneNumberConfirmed = false;

    private String imageId;

    @OneToMany(mappedBy = MapByConstant.USER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = MapByConstant.USER, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @Builder.Default
    private Role role = new Role();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<File> files = new ArrayList<>();
}