package com.example.ecommerce_api.contract;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditEntity<T> {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected Instant createdAt;

    @CreatedBy
    @Column(updatable = false)
    protected T createdBy;

    @LastModifiedDate
    @Column(nullable = false)
    protected Instant updatedAt;

    @LastModifiedBy
    protected T updatedBy;

    @Column(nullable = false)
    protected boolean isDeleted = false;

    // set only when the row is soft-deleted
    protected Instant deletedAt;

    protected T deletedBy;
}
