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
class UpdateUserTypeImplTest {

    @Mock
    private UserTypeGateway userTypeGateway;

    private UpdateUserType useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateUserTypeImpl(userTypeGateway);
    
    }

    @Test
    void shouldUpdateUserType() {
        // given
        UUID id = UUID.randomUUID();
        UserTypeInput input = new UserTypeInput(id, "Updated UserType");

        UserTypeDto expected = new UserTypeDto(id, "Updated UserType");

        when(userTypeGateway.updateUserType(input)).thenReturn(expected);

        // when
        UserTypeDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        verify(userTypeGateway).updateUserType(input);
    }
}
