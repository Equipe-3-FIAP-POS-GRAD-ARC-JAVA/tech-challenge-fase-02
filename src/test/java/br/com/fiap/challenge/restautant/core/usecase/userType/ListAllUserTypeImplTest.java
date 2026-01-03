package br.com.fiap.challenge.restautant.core.usecase.userType;

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

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.gateway.UserTypeGateway;

@ExtendWith(MockitoExtension.class)
class ListAllUserTypeImplTest {

    @Mock
    private UserTypeGateway userTypeGateway;

    private ListAllUserType useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListAllUserTypeImpl(userTypeGateway);
    
    }

    @Test
    void shouldListAllUserType() {
        // given
        List<UserTypeDto> expected = List.of(new UserTypeDto(UUID.randomUUID(), "Test UserType"));

        when(userTypeGateway.getAllUserTypes()).thenReturn(expected);

        // when
        List<UserTypeDto> result = useCase.execute();

        // then
        assertThat(result).isEqualTo(expected);
        verify(userTypeGateway).getAllUserTypes();
    }

}