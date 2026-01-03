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
class ListMenuByIdImplTest {

    @Mock
    private MenuGateway menuGateway;

    private ListMenuById useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListMenuByIdImpl(menuGateway);
    }




    @Test
    void shouldListMenuById() {
        // given
        UUID id = UUID.randomUUID();
        MenuDto expected = new MenuDto(id, UUID.randomUUID(), List.of());

        when(menuGateway.getMenuById(id)).thenReturn(expected);

        // when
        MenuDto result = useCase.execute(id);

        // then
        assertThat(result).isEqualTo(expected);
        verify(menuGateway).getMenuById(id);
    }

}