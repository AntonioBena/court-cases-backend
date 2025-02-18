package com.interview.court.cases.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditingModel {
    @CreatedDate
    @Column(
            name = "created_at",
            updatable = false,
            nullable = false
    )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(
            name = "updated_at",
            insertable = false
    )
    private LocalDateTime updatedAt;
}