package br.com.fiap.challenge.restaurant.core.usecase.openinghours;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.gateway.OpeningHoursGateway;

@ExtendWith(MockitoExtension.class)
class ListOpeningHoursByRestaurantImplTest {

    @Mock
    private OpeningHoursGateway openingHoursGateway;
    
    private ListOpeningHoursByRestaurant useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListOpeningHoursByRestaurantImpl(openingHoursGateway);
    }

    @DisplayName("Should list all opening hours for a restaurant")
    @Test
    void shouldListAllOpeningHoursByRestaurant() {
        // given
        UUID restaurantId = UUID.randomUUID();
        
        List<OpeningHoursDto> expected = List.of(
            new OpeningHoursDto(
                UUID.randomUUID(),
                restaurantId,
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
            ),
            new OpeningHoursDto(
                UUID.randomUUID(),
                restaurantId,
                DayOfWeek.TUESDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
            )
        );

        when(openingHoursGateway.listOpeningHoursByRestaurant(restaurantId)).thenReturn(expected);

        // when
        List<OpeningHoursDto> result = useCase.execute(restaurantId);

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(expected);
        assertThat(result.get(0).weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(result.get(1).weekday()).isEqualTo(DayOfWeek.TUESDAY);
        
        verify(openingHoursGateway).listOpeningHoursByRestaurant(restaurantId);
    }

    @DisplayName("Should return empty list when restaurant has no opening hours")
    @Test
    void shouldReturnEmptyListWhenRestaurantHasNoOpeningHours() {
        // given
        UUID restaurantId = UUID.randomUUID();
        List<OpeningHoursDto> expected = List.of();

        when(openingHoursGateway.listOpeningHoursByRestaurant(restaurantId)).thenReturn(expected);

        // when
        List<OpeningHoursDto> result = useCase.execute(restaurantId);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
        
        verify(openingHoursGateway).listOpeningHoursByRestaurant(restaurantId);
    }
}
