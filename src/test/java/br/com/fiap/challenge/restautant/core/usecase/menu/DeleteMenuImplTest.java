package br.com.fiap.challenge.restautant.core.usecase.menu;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.gateway.MenuGateway;

@ExtendWith(MockitoExtension.class)
class DeleteMenuImplTest {

    @Mock
    private MenuGateway menuGateway;

    private DeleteMenu useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteMenuImpl(menuGateway);
    }




    @DisplayName("Should delete menu")
    @Test
    void shouldDeleteMenu() {
        // given
        UUID id = UUID.randomUUID();

        // when
        useCase.execute(id);

        // then
        verify(menuGateway).deleteMenu(id);
    }

}