package br.com.fiap.challenge.restaurant.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithNoArguments() {
        // When
        NotFoundException exception = new NotFoundException();

        // Then
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateExceptionWithMessage() {
        // Given
        String message = "Entity not found";

        // When
        NotFoundException exception = new NotFoundException(message);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        // Given
        String message = "Entity not found";
        Throwable cause = new RuntimeException("Database error");

        // When
        NotFoundException exception = new NotFoundException(message, cause);

        // Then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldCreateExceptionWithCause() {
        // Given
        Throwable cause = new RuntimeException("Database error");

        // When
        NotFoundException exception = new NotFoundException(cause);

        // Then
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("RuntimeException"));
    }
}
