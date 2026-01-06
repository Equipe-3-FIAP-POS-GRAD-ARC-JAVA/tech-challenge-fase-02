package br.com.fiap.challenge.restaurant.core.usecase.food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.FoodDto;
import br.com.fiap.challenge.restaurant.core.gateway.FoodGateway;

@ExtendWith(MockitoExtension.class)
class ListAllFoodByMenuImplTest {

    @Mock
    private FoodGateway foodGateway;
    private ListAllFoodByMenu useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListAllFoodByMenuImpl(foodGateway);
    }




    @DisplayName("Should list all food by menu")
    @Test
    void shouldListAllFoodByMenu() {
        // given
        UUID menuId = UUID.randomUUID();
        List<FoodDto> expected = List.of(new FoodDto(UUID.randomUUID(), UUID.randomUUID(), "Test Food", "Description", UUID.randomUUID(), 10.0, "image.jpg"));

        when(foodGateway.getFoodsByMenuId(menuId)).thenReturn(expected);

        // when
        List<FoodDto> result = useCase.execute(menuId);

        // then
        assertThat(result).isEqualTo(expected);
        verify(foodGateway).getFoodsByMenuId(menuId);
    }

}