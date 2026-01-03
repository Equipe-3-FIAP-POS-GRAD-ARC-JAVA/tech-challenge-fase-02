package br.com.fiap.challenge.restautant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BaseEntityTest {

    // Concrete implementation for testing abstract BaseEntity
    private static class TestEntity extends BaseEntity {
        public TestEntity() {
            super();
        }

        public TestEntity(UUID id) {
            super(id);
        }
    }

    @Test
    @DisplayName("Should create BaseEntity with default constructor")
    void shouldCreateBaseEntityWithDefaultConstructor() {
        // When
        TestEntity entity = new TestEntity();
        
        // Then
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isNull();
    }

    @Test
    @DisplayName("Should create BaseEntity with id")
    void shouldCreateBaseEntityWithId() {
        // Given
        UUID id = UUID.randomUUID();
        
        // When
        TestEntity entity = new TestEntity(id);
        
        // Then
        assertThat(entity.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Should set and get id")
    void shouldSetAndGetId() {
        // Given
        TestEntity entity = new TestEntity();
        UUID id = UUID.randomUUID();
        
        // When
        entity.setId(id);
        
        // Then
        assertThat(entity.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Should set and get created timestamp")
    void shouldSetAndGetCreatedTimestamp() {
        // Given
        TestEntity entity = new TestEntity();
        OffsetDateTime now = OffsetDateTime.now();
        
        // When
        entity.setCreatedAt(now);
        
        // Then
        assertThat(entity.getCreatedAt()).isEqualTo(now);
    }

    @Test
    @DisplayName("Should set and get updated timestamp")
    void shouldSetAndGetUpdatedTimestamp() {
        // Given
        TestEntity entity = new TestEntity();
        OffsetDateTime now = OffsetDateTime.now();
        
        // When
        entity.setUpdatedAt(now);
        
        // Then
        assertThat(entity.getUpdatedAt()).isEqualTo(now);
    }

    @Test
    @DisplayName("Should set and get deleted timestamp")
    void shouldSetAndGetDeletedTimestamp() {
        // Given
        TestEntity entity = new TestEntity();
        OffsetDateTime now = OffsetDateTime.now();
        
        // When
        entity.setDeletedAt(now);
        
        // Then
        assertThat(entity.getDeletedAt()).isEqualTo(now);
    }

    @Test
    @DisplayName("Should return null for unset timestamps")
    void shouldReturnNullForUnsetTimestamps() {
        // Given
        TestEntity entity = new TestEntity();
        
        // Then
        assertThat(entity.getCreatedAt()).isNull();
        assertThat(entity.getUpdatedAt()).isNull();
        assertThat(entity.getDeletedAt()).isNull();
    }

    @Test
    @DisplayName("Should handle all timestamps independently")
    void shouldHandleAllTimestampsIndependently() {
        // Given
        TestEntity entity = new TestEntity();
        OffsetDateTime created = OffsetDateTime.now();
        OffsetDateTime updated = OffsetDateTime.now().plusHours(1);
        OffsetDateTime deleted = OffsetDateTime.now().plusHours(2);
        
        // When
        entity.setCreatedAt(created);
        entity.setUpdatedAt(updated);
        entity.setDeletedAt(deleted);
        
        // Then
        assertThat(entity.getCreatedAt()).isEqualTo(created);
        assertThat(entity.getUpdatedAt()).isEqualTo(updated);
        assertThat(entity.getDeletedAt()).isEqualTo(deleted);
    }
}
