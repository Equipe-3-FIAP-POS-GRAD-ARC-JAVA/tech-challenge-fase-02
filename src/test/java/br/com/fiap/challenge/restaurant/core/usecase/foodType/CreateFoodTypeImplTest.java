package br.com.fiap.challenge.restaurant.core.usecase.foodType;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restaurant.core.gateway.FoodTypeGateway;

@ExtendWith(MockitoExtension.class)
class CreateFoodTypeImplTest {

    @Mock
    private FoodTypeGateway foodTypeGateway;

    private CreateFoodType useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateFoodTypeImpl(foodTypeGateway);
    }




    @DisplayName("Should create food type")
    @Test
    void shouldCreateFoodType() {
        // given
        FoodTypeInput input = new FoodTypeInput(null, "Test FoodType");

        FoodTypeDto expected = new FoodTypeDto(UUID.randomUUID(), "Test FoodType");

        when(foodTypeGateway.createFoodType(input)).thenReturn(expected);

        // when
        FoodTypeDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(foodTypeGateway).createFoodType(input);
    }

}