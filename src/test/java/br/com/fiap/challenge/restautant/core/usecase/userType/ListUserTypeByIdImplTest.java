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
import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;

@ExtendWith(MockitoExtension.class)
class ListUserTypeByIdImplTest {

    @Mock
    private UserTypeGateway userTypeGateway;

    private ListUserTypeById useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListUserTypeByIdImpl(userTypeGateway);
    }

    @Test
    void shouldListUserTypeById() {
        // given
        UUID id = UUID.randomUUID();
        UserTypeDto expected = new UserTypeDto(id, "Test UserType");

        when(userTypeGateway.getUserTypeById(id)).thenReturn(expected);

        // when
        UserTypeDto result = useCase.execute(id);

        // then
        assertThat(result).isEqualTo(expected);
        verify(userTypeGateway).getUserTypeById(id);
    }

}
