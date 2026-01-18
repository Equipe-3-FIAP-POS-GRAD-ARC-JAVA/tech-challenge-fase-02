package br.com.fiap.challenge.restaurant.core.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.FoodDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodInput;
import br.com.fiap.challenge.restaurant.core.gateway.FoodGateway;

@ExtendWith(MockitoExtension.class)
class CreatedFoodImplTest {

    @Mock
    private FoodGateway foodGateway;
    private CreatedFood useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreatedFoodImpl(foodGateway);
    }




    @DisplayName("Should create food")
    @Test
    void shouldCreateFood() {
        // given
        FoodInput input = new FoodInput(null, UUID.randomUUID(), "Test Food", "Description", UUID.randomUUID(), 10.0, "image.jpg");

        FoodDto expected = new FoodDto(UUID.randomUUID(), UUID.randomUUID(), "Test Food", "Description", UUID.randomUUID(), 10.0, "image.jpg");

        when(foodGateway.createFood(input)).thenReturn(expected);

        // when
        FoodDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(foodGateway).createFood(input);
    }

}