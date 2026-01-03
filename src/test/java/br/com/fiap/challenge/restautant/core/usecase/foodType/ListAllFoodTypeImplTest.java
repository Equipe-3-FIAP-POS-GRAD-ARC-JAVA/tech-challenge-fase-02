package br.com.fiap.challenge.restautant.core.usecase.foodType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.gateway.FoodTypeGateway;

@ExtendWith(MockitoExtension.class)
class ListAllFoodTypeImplTest {

    @Mock
    private FoodTypeGateway foodTypeGateway;

    private ListAllFoodType useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListAllFoodTypeImpl(foodTypeGateway);
    }




    @Test
    void shouldListAllFoodType() {
        // given
        List<FoodTypeDto> expected = List.of(new FoodTypeDto(UUID.randomUUID(), "Test FoodType"));

        when(foodTypeGateway.getAllFoodTypes()).thenReturn(expected);

        // when
        List<FoodTypeDto> result = useCase.execute();

        // then
        assertThat(result).isEqualTo(expected);
        verify(foodTypeGateway).getAllFoodTypes();
    }

}