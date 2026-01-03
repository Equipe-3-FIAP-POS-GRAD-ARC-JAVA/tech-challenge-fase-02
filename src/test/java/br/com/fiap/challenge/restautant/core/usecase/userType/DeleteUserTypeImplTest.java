package br.com.fiap.challenge.restautant.core.usecase.userType;

import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;

@ExtendWith(MockitoExtension.class)
class DeleteUserTypeImplTest {

    @Mock
    private UserTypeGateway userTypeGateway;

    private DeleteUserType useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteUserTypeImpl(userTypeGateway);
    }

    @Test
    void shouldDeleteUserType() {
        // given
        UUID id = UUID.randomUUID();

        // when
        useCase.execute(id);

        // then
        verify(userTypeGateway).deleteUserType(id);
    }

}
