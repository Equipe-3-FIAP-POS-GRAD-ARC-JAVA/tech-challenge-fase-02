package br.com.fiap.challenge.restautant.core.domain.entity;

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

        public void setCreatedAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(OffsetDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

        public void setDeletedAt(OffsetDateTime deletedAt) {
            this.deletedAt = deletedAt;
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
    @DisplayName("Should get created timestamp")
    void shouldGetCreatedTimestamp() {
        // Given
        TestEntity entity = new TestEntity();
        OffsetDateTime now = OffsetDateTime.now();
        entity.setCreatedAt(now);
        
        // When
        OffsetDateTime createdAt = entity.getCreatedAt();
        
        // Then
        assertThat(createdAt).isEqualTo(now);
    }

    @Test
    @DisplayName("Should get updated timestamp")
    void shouldGetUpdatedTimestamp() {
        // Given
        TestEntity entity = new TestEntity();
        OffsetDateTime now = OffsetDateTime.now();
        entity.setUpdatedAt(now);
        
        // When
        OffsetDateTime updatedAt = entity.getUpdatedAt();
        
        // Then
        assertThat(updatedAt).isEqualTo(now);
    }

    @Test
    @DisplayName("Should get deleted timestamp")
    void shouldGetDeletedTimestamp() {
        // Given
        TestEntity entity = new TestEntity();
        OffsetDateTime now = OffsetDateTime.now();
        entity.setDeletedAt(now);
        
        // When
        OffsetDateTime deletedAt = entity.getDeletedAt();
        
        // Then
        assertThat(deletedAt).isEqualTo(now);
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
}
