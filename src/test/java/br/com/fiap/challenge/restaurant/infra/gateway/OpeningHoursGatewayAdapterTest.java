package br.com.fiap.challenge.restaurant.infra.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;
import br.com.fiap.challenge.restaurant.infra.entity.Address;
import br.com.fiap.challenge.restaurant.infra.entity.OpeningHours;
import br.com.fiap.challenge.restaurant.infra.entity.Restaurant;
import br.com.fiap.challenge.restaurant.infra.entity.State;
import br.com.fiap.challenge.restaurant.infra.entity.ZipCode;
import br.com.fiap.challenge.restaurant.infra.repository.OpeningHoursRepository;
import br.com.fiap.challenge.restaurant.infra.repository.RestaurantRepository;

@ExtendWith(MockitoExtension.class)
class OpeningHoursGatewayAdapterTest {

    @Mock
    private OpeningHoursRepository openingHoursRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    private OpeningHoursGatewayAdapter gateway;

    @BeforeEach
    void setUp() {
        gateway = new OpeningHoursGatewayAdapter(openingHoursRepository, restaurantRepository);
    }

    @Test
    @DisplayName("Should create opening hours")
    void shouldCreateOpeningHours() {
        UUID restaurantId = UUID.randomUUID();
        UUID openingHoursId = UUID.randomUUID();

        Restaurant restaurant = new Restaurant(
                "Test Restaurant",
                new Address("Street", "123", "City", "Neighborhood", "Country",
                        new State("State"), new ZipCode("12345"))
        );
        restaurant.setId(restaurantId);

        OpeningHoursInput input = new OpeningHoursInput(
                restaurantId,
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );

        OpeningHours saved = new OpeningHours(
                restaurant,
                input.weekday(),
                input.opensAt(),
                input.closesAt(),
                input.isClosed()
        );
        saved.setId(openingHoursId);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(openingHoursRepository.save(any(OpeningHours.class))).thenReturn(saved);

        OpeningHoursDto result = gateway.createOpeningHours(input);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(openingHoursId);
        assertThat(result.restaurantId()).isEqualTo(restaurantId);
        assertThat(result.weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(result.opensAt()).isEqualTo(LocalTime.of(9, 0));
        assertThat(result.closesAt()).isEqualTo(LocalTime.of(18, 0));
        assertThat(result.isClosed()).isFalse();
    }

    @Test
    @DisplayName("Should throw when creating opening hours and restaurant not found")
    void shouldThrowWhenCreateRestaurantNotFound() {
        UUID restaurantId = UUID.randomUUID();

        OpeningHoursInput input = new OpeningHoursInput(
                restaurantId,
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gateway.createOpeningHours(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Restaurant not found");
    }

    @Test
    @DisplayName("Should update opening hours")
    void shouldUpdateOpeningHours() {
        UUID restaurantId = UUID.randomUUID();
        UUID openingHoursId = UUID.randomUUID();

        Restaurant restaurant = new Restaurant(
                "Test Restaurant",
                new Address("Street", "123", "City", "Neighborhood", "Country",
                        new State("State"), new ZipCode("12345"))
        );
        restaurant.setId(restaurantId);

        OpeningHours existing = new OpeningHours(
                restaurant,
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );
        existing.setId(openingHoursId);

        // OBS: seu adapter chama input.weekda() (sem o "y").
        // Esse teste assume que o record OpeningHoursUpdateInput possui esse método.
        OpeningHoursUpdateInput input = new OpeningHoursUpdateInput(
                openingHoursId,
                restaurantId,
                DayOfWeek.FRIDAY,
                LocalTime.of(10, 0),
                LocalTime.of(20, 0),
                true
        );

        when(openingHoursRepository.findById(openingHoursId)).thenReturn(Optional.of(existing));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(openingHoursRepository.save(any(OpeningHours.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OpeningHoursDto result = gateway.updateOpeningHours(input);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(openingHoursId);
        assertThat(result.restaurantId()).isEqualTo(restaurantId);
        assertThat(result.weekday()).isEqualTo(DayOfWeek.FRIDAY);
        assertThat(result.opensAt()).isEqualTo(LocalTime.of(10, 0));
        assertThat(result.closesAt()).isEqualTo(LocalTime.of(20, 0));
        assertThat(result.isClosed()).isTrue();

        verify(openingHoursRepository).save(existing);
    }

    @Test
    @DisplayName("Should throw when updating opening hours and opening hours not found")
    void shouldThrowWhenUpdateOpeningHoursNotFound() {
        UUID openingHoursId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        OpeningHoursUpdateInput input = new OpeningHoursUpdateInput(
                openingHoursId,
                restaurantId,
                DayOfWeek.FRIDAY,
                LocalTime.of(10, 0),
                LocalTime.of(20, 0),
                true
        );

        when(openingHoursRepository.findById(openingHoursId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gateway.updateOpeningHours(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("OpeningHours not found");
    }

    @Test
    @DisplayName("Should list opening hours by restaurant id")
    void shouldListOpeningHoursByRestaurant() {
        UUID restaurantId = UUID.randomUUID();

        Restaurant restaurant = new Restaurant(
                "Test Restaurant",
                new Address("Street", "123", "City", "Neighborhood", "Country",
                        new State("State"), new ZipCode("12345"))
        );
        restaurant.setId(restaurantId);

        UUID oh1Id = UUID.randomUUID();
        UUID oh2Id = UUID.randomUUID();

        OpeningHours oh1 = new OpeningHours(
                restaurant,
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );
        oh1.setId(oh1Id);

        OpeningHours oh2 = new OpeningHours(
                restaurant,
                DayOfWeek.SUNDAY,
                null,
                null,
                true
        );
        oh2.setId(oh2Id);

        when(openingHoursRepository.findByRestaurantId(restaurantId)).thenReturn(List.of(oh1, oh2));

        List<OpeningHoursDto> result = gateway.listOpeningHoursByRestaurant(restaurantId);

        assertThat(result).hasSize(2);

        assertThat(result.get(0).id()).isEqualTo(oh1Id);
        assertThat(result.get(0).restaurantId()).isEqualTo(restaurantId);
        assertThat(result.get(0).weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(result.get(0).opensAt()).isEqualTo(LocalTime.of(9, 0));
        assertThat(result.get(0).closesAt()).isEqualTo(LocalTime.of(18, 0));
        assertThat(result.get(0).isClosed()).isFalse();

        assertThat(result.get(1).id()).isEqualTo(oh2Id);
        assertThat(result.get(1).restaurantId()).isEqualTo(restaurantId);
        assertThat(result.get(1).weekday()).isEqualTo(DayOfWeek.SUNDAY);
        assertThat(result.get(1).opensAt()).isNull();
        assertThat(result.get(1).closesAt()).isNull();
        assertThat(result.get(1).isClosed()).isTrue();
    }
}
