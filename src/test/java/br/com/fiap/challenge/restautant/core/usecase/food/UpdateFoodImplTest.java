package br.com.fiap.challenge.restautant.core.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import br.com.fiap.challenge.restautant.core.gateway.FoodGateway;

@ExtendWith(MockitoExtension.class)
class UpdateFoodImplTest {

    @Mock
    private FoodGateway foodGateway;

    private UpdateFood useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateFoodImpl(foodGateway);
    }




    @Test
    void shouldUpdateFood() {
        // given
        UUID id = UUID.randomUUID();
        FoodInput input = new FoodInput(id, UUID.randomUUID(), "Updated Food", "Description", UUID.randomUUID(), 15.0, "image.jpg");

        FoodDto expected = new FoodDto(id, UUID.randomUUID(), "Updated Food", "Description", UUID.randomUUID(), 15.0, "image.jpg");

        when(foodGateway.updateFood(input)).thenReturn(expected);

        // when
        FoodDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(foodGateway).updateFood(input);
    }

}