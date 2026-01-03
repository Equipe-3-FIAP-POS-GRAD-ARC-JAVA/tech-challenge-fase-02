package br.com.fiap.challenge.restautant.core.usecase.foodType;

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

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restautant.core.gateway.FoodTypeGateway;

@ExtendWith(MockitoExtension.class)
class UpdateFoodTypeImplTest {

    @Mock
    private FoodTypeGateway foodTypeGateway;

    private UpdateFoodType useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateFoodTypeImpl(foodTypeGateway);
    }




    @DisplayName("Should update food type")
    @Test
    void shouldUpdateFoodType() {
        // given
        UUID id = UUID.randomUUID();
        FoodTypeInput input = new FoodTypeInput(id, "Updated FoodType");

        FoodTypeDto expected = new FoodTypeDto(id, "Updated FoodType");

        when(foodTypeGateway.updateFoodType(input)).thenReturn(expected);

        // when
        FoodTypeDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(foodTypeGateway).updateFoodType(input);
    }

}