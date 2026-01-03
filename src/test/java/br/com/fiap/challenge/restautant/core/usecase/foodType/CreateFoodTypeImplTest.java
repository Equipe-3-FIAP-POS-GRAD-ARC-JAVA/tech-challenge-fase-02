package br.com.fiap.challenge.restautant.core.usecase.foodType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restautant.core.gateway.FoodTypeGateway;

@ExtendWith(MockitoExtension.class)
class CreateFoodTypeImplTest {

    @Mock
    private FoodTypeGateway foodTypeGateway;

    private CreateFoodType useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateFoodTypeImpl(foodTypeGateway);
    }




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