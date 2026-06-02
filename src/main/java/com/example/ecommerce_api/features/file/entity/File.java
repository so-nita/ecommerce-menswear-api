package com.example.ecommerce_api.features.file.entity;

import com.example.ecommerce_api.constant.EntityConstant;
import com.example.ecommerce_api.contract.BaseEntity;
import com.example.ecommerce_api.features.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = EntityConstant.IMAGE)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private User createdBy;

    @Column(nullable = false)
    private String originName;

    @Column(nullable = false)
    private String generatedName;

    private String category;
    private String extension;

    @Column
    private String contentType;

}
