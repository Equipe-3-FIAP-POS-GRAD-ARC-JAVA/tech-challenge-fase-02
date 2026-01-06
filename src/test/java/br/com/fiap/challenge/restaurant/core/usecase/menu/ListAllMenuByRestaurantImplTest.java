package br.com.fiap.challenge.restaurant.core.usecase.menu;

import java.util.List;
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

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.gateway.MenuGateway;

@ExtendWith(MockitoExtension.class)
class ListAllMenuByRestaurantImplTest {

    @Mock
    private MenuGateway menuGateway;

    private ListAllMenuByRestaurant useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListAllMenuByRestaurantImpl(menuGateway);
    }




    @DisplayName("Should list all menu by restaurant")
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