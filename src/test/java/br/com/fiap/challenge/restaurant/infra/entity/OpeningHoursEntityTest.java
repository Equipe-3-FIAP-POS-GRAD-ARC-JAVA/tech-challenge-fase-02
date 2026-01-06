package br.com.fiap.challenge.restaurant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OpeningHoursEntityTest {

    @Test
    @DisplayName("Should create OpeningHours with restaurant, weekday, opensAt, closesAt and isClosed")
    void shouldCreateOpeningHours() {
        // Given
        Restaurant restaurant = new Restaurant(
                "Restaurant Test",
                new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                        new State("SP"), new ZipCode("12345-678"))
        );

        DayOfWeek weekday = DayOfWeek.MONDAY;
        LocalTime opensAt = LocalTime.of(9, 0);
        LocalTime closesAt = LocalTime.of(18, 0);
        boolean isClosed = false;

        // When
        OpeningHours openingHours = new OpeningHours(restaurant, weekday, opensAt, closesAt, isClosed);

        // Then
        assertThat(openingHours.getRestaurant()).isEqualTo(restaurant);
        assertThat(openingHours.getWeekday()).isEqualTo(weekday);
        assertThat(openingHours.getOpensAt()).isEqualTo(opensAt);
        assertThat(openingHours.getClosesAt()).isEqualTo(closesAt);
        assertThat(openingHours.isClosed()).isEqualTo(isClosed);
    }

    @Test
    @DisplayName("Should update restaurant")
    void shouldUpdateRestaurant() {
        // Given
        Restaurant restaurant = new Restaurant(
                "Restaurant Test",
                new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                        new State("SP"), new ZipCode("12345-678"))
        );

        OpeningHours openingHours = new OpeningHours(
                restaurant,
                DayOfWeek.TUESDAY,
                LocalTime.of(10, 0),
                LocalTime.of(22, 0),
                false
        );

        Restaurant newRestaurant = new Restaurant(
                "New Restaurant",
                new Address("Rua Nova", "456", "Rio de Janeiro", "Copacabana", "Brasil",
                        new State("RJ"), new ZipCode("98765-432"))
        );

        // When
        openingHours.setRestaurant(newRestaurant);

        // Then
        assertThat(openingHours.getRestaurant()).isEqualTo(newRestaurant);
        assertThat(openingHours.getRestaurant().getName()).isEqualTo("New Restaurant");
    }

    @Test
    @DisplayName("Should update weekday")
    void shouldUpdateWeekday() {
        // Given
        OpeningHours openingHours = new OpeningHours(
                new Restaurant("Restaurant Test",
                        new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                                new State("SP"), new ZipCode("12345-678"))),
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );

        // When
        openingHours.setWeekday(DayOfWeek.FRIDAY);

        // Then
        assertThat(openingHours.getWeekday()).isEqualTo(DayOfWeek.FRIDAY);
    }

    @Test
    @DisplayName("Should update opensAt")
    void shouldUpdateOpensAt() {
        // Given
        OpeningHours openingHours = new OpeningHours(
                new Restaurant("Restaurant Test",
                        new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                                new State("SP"), new ZipCode("12345-678"))),
                DayOfWeek.WEDNESDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );

        // When
        openingHours.setOpensAt(LocalTime.of(8, 30));

        // Then
        assertThat(openingHours.getOpensAt()).isEqualTo(LocalTime.of(8, 30));
    }

    @Test
    @DisplayName("Should update closesAt")
    void shouldUpdateClosesAt() {
        // Given
        OpeningHours openingHours = new OpeningHours(
                new Restaurant("Restaurant Test",
                        new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                                new State("SP"), new ZipCode("12345-678"))),
                DayOfWeek.THURSDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );

        // When
        openingHours.setClosesAt(LocalTime.of(19, 0));

        // Then
        assertThat(openingHours.getClosesAt()).isEqualTo(LocalTime.of(19, 0));
    }

    @Test
    @DisplayName("Should update isClosed")
    void shouldUpdateIsClosed() {
        // Given
        OpeningHours openingHours = new OpeningHours(
                new Restaurant("Restaurant Test",
                        new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                                new State("SP"), new ZipCode("12345-678"))),
                DayOfWeek.SATURDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );

        // When
        openingHours.setClosed(true);

        // Then
        assertThat(openingHours.isClosed()).isTrue();
    }

    @Test
    @DisplayName("Should allow null opensAt/closesAt when isClosed is true")
    void shouldAllowNullTimesWhenClosed() {
        // Given
        Restaurant restaurant = new Restaurant(
                "Restaurant Test",
                new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                        new State("SP"), new ZipCode("12345-678"))
        );

        // When
        OpeningHours openingHours = new OpeningHours(
                restaurant,
                DayOfWeek.SUNDAY,
                null,
                null,
                true
        );

        // Then
        assertThat(openingHours.getRestaurant()).isEqualTo(restaurant);
        assertThat(openingHours.getWeekday()).isEqualTo(DayOfWeek.SUNDAY);
        assertThat(openingHours.getOpensAt()).isNull();
        assertThat(openingHours.getClosesAt()).isNull();
        assertThat(openingHours.isClosed()).isTrue();
    }
}
