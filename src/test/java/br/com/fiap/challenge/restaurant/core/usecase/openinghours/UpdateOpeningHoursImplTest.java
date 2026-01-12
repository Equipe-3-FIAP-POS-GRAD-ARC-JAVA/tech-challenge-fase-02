package br.com.fiap.challenge.restaurant.core.usecase.openinghours;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;
import br.com.fiap.challenge.restaurant.core.gateway.OpeningHoursGateway;

@ExtendWith(MockitoExtension.class)
class UpdateOpeningHoursImplTest {

    @Mock
    private OpeningHoursGateway openingHoursGateway;
    
    private UpdateOpeningHours useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateOpeningHoursImpl(openingHoursGateway);
    }

    @DisplayName("Should update opening hours")
    @Test
    void shouldUpdateOpeningHours() {
        // given
        UUID id = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        
        OpeningHoursUpdateInput input = new OpeningHoursUpdateInput(
            id,
            restaurantId,
            DayOfWeek.MONDAY,
            LocalTime.of(10, 0),
            LocalTime.of(22, 0),
            false
        );

        OpeningHoursDto expected = new OpeningHoursDto(
            id,
            restaurantId,
            DayOfWeek.MONDAY,
            LocalTime.of(10, 0),
            LocalTime.of(22, 0),
            false
        );

        when(openingHoursGateway.updateOpeningHours(input)).thenReturn(expected);

        // when
        OpeningHoursDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(result.id()).isEqualTo(id);
        assertThat(result.opensAt()).isEqualTo(LocalTime.of(10, 0));
        assertThat(result.closesAt()).isEqualTo(LocalTime.of(22, 0));
        assertThat(result.isClosed()).isFalse();
        
        verify(openingHoursGateway).updateOpeningHours(input);
    }

    @DisplayName("Should update opening hours to closed")
    @Test
    void shouldUpdateOpeningHoursToClosed() {
        // given
        UUID id = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        
        OpeningHoursUpdateInput input = new OpeningHoursUpdateInput(
            id,
            restaurantId,
            DayOfWeek.SUNDAY,
            null,
            null,
            true
        );

        OpeningHoursDto expected = new OpeningHoursDto(
            id,
            restaurantId,
            DayOfWeek.SUNDAY,
            null,
            null,
            true
        );

        when(openingHoursGateway.updateOpeningHours(input)).thenReturn(expected);

        // when
        OpeningHoursDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(result.isClosed()).isTrue();
        assertThat(result.opensAt()).isNull();
        assertThat(result.closesAt()).isNull();
        
        verify(openingHoursGateway).updateOpeningHours(input);
    }
}
