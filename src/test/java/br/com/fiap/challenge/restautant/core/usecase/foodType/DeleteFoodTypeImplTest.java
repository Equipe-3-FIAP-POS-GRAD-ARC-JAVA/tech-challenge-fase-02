package br.com.fiap.challenge.restautant.core.usecase.foodType;

import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.gateway.FoodTypeGateway;

@ExtendWith(MockitoExtension.class)
class DeleteFoodTypeImplTest {

    @Mock
    private FoodTypeGateway foodTypeGateway;

    private DeleteFoodType useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteFoodTypeImpl(foodTypeGateway);
    }




    @Test
    void shouldDeleteFoodType() {
        // given
        UUID id = UUID.randomUUID();

        // when
        useCase.execute(id);

        // then
        verify(foodTypeGateway).deleteFoodType(id);
    }

}