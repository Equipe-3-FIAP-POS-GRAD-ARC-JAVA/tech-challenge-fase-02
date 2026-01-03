package br.com.fiap.challenge.restautant.core.usecase.foodType;

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

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.gateway.FoodTypeGateway;

@ExtendWith(MockitoExtension.class)
class ListFoodTypeByIdImplTest {

    @Mock
    private FoodTypeGateway foodTypeGateway;

    private ListFoodTypeById useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListFoodTypeByIdImpl(foodTypeGateway);
    }

    @Test
    @DisplayName("Should list food type by id")
    void shouldListFoodTypeById() {
        // given
        UUID id = UUID.randomUUID();
        FoodTypeDto expected = new FoodTypeDto(id, "Food Type Name");

        when(foodTypeGateway.getFoodTypeById(id)).thenReturn(expected);

        // when
        FoodTypeDto result = useCase.execute(id);

        // then
        assertThat(result).isEqualTo(expected);
        verify(foodTypeGateway).getFoodTypeById(id);
    }

}