package br.com.fiap.challenge.restautant.core.usecase.userType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.dto.UserTypeInput;
import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;

@ExtendWith(MockitoExtension.class)
class CreateUserTypeImplTest {

    @Mock
    private UserTypeGateway userTypeGateway;

    private CreateUserType useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateUserTypeImpl(userTypeGateway);
    }

    @Test
    void shouldCreateUserType() {
        // given
        UserTypeInput input = new UserTypeInput(null, "Test UserType");

        UserTypeDto expected = new UserTypeDto(UUID.randomUUID(), "Test UserType");

        when(userTypeGateway.createUserType(input)).thenReturn(expected);

        // when
        UserTypeDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(userTypeGateway).createUserType(input);
    }
}
