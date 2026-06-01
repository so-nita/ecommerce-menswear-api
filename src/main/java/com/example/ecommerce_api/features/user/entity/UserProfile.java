package com.example.ecommerce_api.features.user.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.constant.ForeignKeyConstant;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = EntityConstant.USER_PROFILE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = ForeignKeyConstant.USER_ID, nullable = false)
    private User user;
}
