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
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.gateway.OpeningHoursGateway;

@ExtendWith(MockitoExtension.class)
class CreateOpeningHoursImplTest {

    @Mock
    private OpeningHoursGateway openingHoursGateway;
    
    private CreateOpeningHours useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateOpeningHoursImpl(openingHoursGateway);
    }

    @DisplayName("Should create opening hours")
    @Test
    void shouldCreateOpeningHours() {
        // given
        UUID restaurantId = UUID.randomUUID();
        OpeningHoursInput input = new OpeningHoursInput(
            restaurantId,
            DayOfWeek.MONDAY,
            LocalTime.of(9, 0),
            LocalTime.of(18, 0),
            false
        );

        UUID createdId = UUID.randomUUID();
        OpeningHoursDto expected = new OpeningHoursDto(
            createdId,
            restaurantId,
            DayOfWeek.MONDAY,
            LocalTime.of(9, 0),
            LocalTime.of(18, 0),
            false
        );

        when(openingHoursGateway.createOpeningHours(input)).thenReturn(expected);

        // when
        OpeningHoursDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(result.id()).isEqualTo(createdId);
        assertThat(result.restaurantId()).isEqualTo(restaurantId);
        assertThat(result.weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(result.opensAt()).isEqualTo(LocalTime.of(9, 0));
        assertThat(result.closesAt()).isEqualTo(LocalTime.of(18, 0));
        assertThat(result.isClosed()).isFalse();
        
        verify(openingHoursGateway).createOpeningHours(input);
    }

    @DisplayName("Should create opening hours for closed day")
    @Test
    void shouldCreateOpeningHoursForClosedDay() {
        // given
        UUID restaurantId = UUID.randomUUID();
        OpeningHoursInput input = new OpeningHoursInput(
            restaurantId,
            DayOfWeek.SUNDAY,
            null,
            null,
            true
        );

        UUID createdId = UUID.randomUUID();
        OpeningHoursDto expected = new OpeningHoursDto(
            createdId,
            restaurantId,
            DayOfWeek.SUNDAY,
            null,
            null,
            true
        );

        when(openingHoursGateway.createOpeningHours(input)).thenReturn(expected);

        // when
        OpeningHoursDto result = useCase.execute(input);

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(result.isClosed()).isTrue();
        assertThat(result.opensAt()).isNull();
        assertThat(result.closesAt()).isNull();
        
        verify(openingHoursGateway).createOpeningHours(input);
    }
}
