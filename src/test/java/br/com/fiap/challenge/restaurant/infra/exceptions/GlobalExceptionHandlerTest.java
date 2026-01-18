package br.com.fiap.challenge.restaurant.infra.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ProblemDetail;

import br.com.fiap.challenge.restaurant.core.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Test
    @DisplayName("Should handle NotFoundException")
    void shouldHandleNotFoundException() {
        NotFoundException exception = new NotFoundException("Test resource not found");

        ProblemDetail result = handler.handleNotFoundException(exception);

        assertThat(result.getTitle()).isEqualTo("Resource Not Found");
        assertThat(result.getDetail()).isEqualTo("Test resource not found");
        assertThat(result.getStatus()).isEqualTo(404);
    }
}