package br.com.fiap.challenge.restautant.core.usecase.food;

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

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.gateway.FoodGateway;

@ExtendWith(MockitoExtension.class)
class ListFoodByIdImplTest {

    @Mock
    private FoodGateway foodGateway;

    private ListFoodById useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListFoodByIdImpl(foodGateway);
    }




    @DisplayName("Should list food by id")
    @Test
    void shouldListFoodById() {
        // given
        UUID id = UUID.randomUUID();
        FoodDto expected = new FoodDto(id, UUID.randomUUID(), "Test Food", "Description", UUID.randomUUID(), 10.0, "image.jpg");

        when(foodGateway.getFoodById(id)).thenReturn(expected);

        // when
        FoodDto result = useCase.execute(id);

        // then
        assertThat(result).isEqualTo(expected);
        verify(foodGateway).getFoodById(id);
    }

}