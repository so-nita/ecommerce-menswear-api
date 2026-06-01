package com.example.ecommerce_api.contract;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(nullable = false, updatable = false)
    protected OffsetDateTime createdAt;

    @Column(nullable = false)
    protected boolean isDeleted;

    @PrePersist
    public void onCreate() {
        this.createdAt = OffsetDateTime.now();
    }

}

