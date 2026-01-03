package br.com.fiap.challenge.restautant.core.domain.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

public abstract class BaseEntity {

    protected UUID id;
    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;
    protected OffsetDateTime deletedAt;

    protected BaseEntity(UUID id) {
        this.id = id;
    }

    protected BaseEntity() {
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OffsetDateTime getDeletedAt() {
        return deletedAt;
    }
}