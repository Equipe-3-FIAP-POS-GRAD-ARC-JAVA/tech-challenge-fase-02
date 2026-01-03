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




    @Test
    void shouldUpdateMenu() {
        // given
        UUID id = UUID.randomUUID();
        MenuInput input = new MenuInput(id, "Updated Restaurant");

        MenuDto expected = new MenuDto(id, UUID.randomUUID(), List.of());

        when(menuGateway.updateMenu(input)).thenReturn(expected);

        // when
        MenuDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(menuGateway).updateMenu(input);
    }

}