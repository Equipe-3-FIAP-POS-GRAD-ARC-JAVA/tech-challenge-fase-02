package br.com.fiap.challenge.restautant.core.usecase.menu;

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

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.dto.MenuInput;
import br.com.fiap.challenge.restautant.core.gateway.MenuGateway;

@ExtendWith(MockitoExtension.class)
class UpdateMenuImplTest {

    @Mock
    private MenuGateway menuGateway;

    private UpdateMenu useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateMenuImpl(menuGateway);
    }




    @DisplayName("Should update menu")
    @Test
    void shouldUpdateMenu() {
        // given
        UUID restaurantId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();

        MenuInput input = new MenuInput(menuId, restaurantId);

        MenuDto expected = new MenuDto(menuId, restaurantId, List.of());

        when(menuGateway.updateMenu(input)).thenReturn(expected);

        // when
        MenuDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(menuGateway).updateMenu(input);
    }

}