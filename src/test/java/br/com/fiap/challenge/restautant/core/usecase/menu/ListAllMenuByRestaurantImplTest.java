package br.com.fiap.challenge.restautant.core.usecase.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.gateway.MenuGateway;

@ExtendWith(MockitoExtension.class)
class ListAllMenuByRestaurantImplTest {

    @Mock
    private MenuGateway menuGateway;

    private ListAllMenuByRestaurant useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListAllMenuByRestaurantImpl(menuGateway);
    }




    @Test
    void shouldListAllMenuByRestaurant() {
        // given
        UUID restaurantId = UUID.randomUUID();
        List<MenuDto> expected = List.of(new MenuDto(UUID.randomUUID(), restaurantId, List.of()));

        when(menuGateway.getMenusByRestaurantId(restaurantId)).thenReturn(expected);

        // when
        List<MenuDto> result = useCase.execute(restaurantId);

        // then
        assertThat(result).isEqualTo(expected);
        verify(menuGateway).getMenusByRestaurantId(restaurantId);
    }

}