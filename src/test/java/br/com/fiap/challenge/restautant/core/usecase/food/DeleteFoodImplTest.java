package br.com.fiap.challenge.restautant.core.usecase.food;

import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.gateway.FoodGateway;

@ExtendWith(MockitoExtension.class)
class DeleteFoodImplTest {

    @Mock
    private FoodGateway foodGateway;
    private DeleteFood useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteFoodImpl(foodGateway);
    }




    @Test
    void shouldDeleteFood() {
        // given
        UUID id = UUID.randomUUID();

        // when
        useCase.execute(id);

        // then
        verify(foodGateway).deleteFood(id);
    }

}