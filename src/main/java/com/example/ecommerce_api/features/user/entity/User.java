package com.example.ecommerce_api.features.user.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.constant.ForeignKeyConstant;
import com.example.ecommerce_api.features.address.entity.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = EntityConstant.USER)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed = false;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(name = "phone_number_confirmed", nullable = false)
    private boolean phoneNumberConfirmed = false;

    // --- Security / lockout (equivalent to IdentityUser lockout fields) ---
    @Column(name = "two_factor_enabled", nullable = false)
    private boolean twoFactorEnabled = false;

    @Column(name = "lockout_end")
    private Instant lockoutEnd;

    @Column(name = "lockout_enabled", nullable = false)
    private boolean lockoutEnabled = true;

    @Column(name = "access_failed_count", nullable = false)
    private int accessFailedCount = 0;

    @Column(name = "security_stamp")
    private String securityStamp;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

    // Relational
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = ForeignKeyConstant.FK_USER_ID),
        inverseJoinColumns = @JoinColumn(name = ForeignKeyConstant.FK_ROLE_ID)
    )
    private Set<Role> roles = new HashSet<>();
}