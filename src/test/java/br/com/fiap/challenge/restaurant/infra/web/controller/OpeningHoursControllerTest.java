package br.com.fiap.challenge.restaurant.infra.web.controller;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.CreateOpeningHours;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.ListOpeningHoursByRestaurant;
import br.com.fiap.challenge.restaurant.core.usecase.openinghours.UpdateOpeningHours;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpeningHoursControllerTest {

    @InjectMocks
    private OpeningHoursController openingHoursController;

    @Mock
    private CreateOpeningHours createOpeningHours;

    @Mock
    private UpdateOpeningHours updateOpeningHours;

    @Mock
    private ListOpeningHoursByRestaurant listOpeningHoursByRestaurant;

    @Test
    @DisplayName("Should create opening hours successfully")
    void shouldCreateOpeningHours() {
        UUID openingHoursId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        OpeningHoursInput input = new OpeningHoursInput(
                restaurantId,
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );

        OpeningHoursDto expected = new OpeningHoursDto(
                openingHoursId,
                restaurantId,
                DayOfWeek.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                false
        );

        when(createOpeningHours.execute(any(OpeningHoursInput.class))).thenReturn(expected);

        ResponseEntity<OpeningHoursDto> response = openingHoursController.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isEqualTo(openingHoursId);
        assertThat(response.getBody().restaurantId()).isEqualTo(restaurantId);
        assertThat(response.getBody().weekday()).isEqualTo(DayOfWeek.MONDAY);
        verify(createOpeningHours).execute(any(OpeningHoursInput.class));
    }

    @Test
    @DisplayName("Should list opening hours by restaurant successfully")
    void shouldListOpeningHoursByRestaurant() {
        UUID restaurantId = UUID.randomUUID();
        UUID openingHoursId = UUID.randomUUID();

        OpeningHoursDto dto = new OpeningHoursDto(
                openingHoursId,
                restaurantId,
                DayOfWeek.FRIDAY,
                LocalTime.of(10, 0),
                LocalTime.of(20, 0),
                false
        );

        when(listOpeningHoursByRestaurant.execute(restaurantId)).thenReturn(List.of(dto));

        ResponseEntity<List<OpeningHoursDto>> response = openingHoursController.listByRestaurant(restaurantId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(1);
        assertThat(response.getBody().get(0).id()).isEqualTo(openingHoursId);
        assertThat(response.getBody().get(0).restaurantId()).isEqualTo(restaurantId);
        verify(listOpeningHoursByRestaurant).execute(restaurantId);
    }

    @Test
    @DisplayName("Should update opening hours successfully and use path id")
    void shouldUpdateOpeningHours() {
        UUID pathId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        // input com um id diferente (pra garantir que o controller sobrescreve pelo pathId)
        UUID bodyIdDifferent = UUID.randomUUID();

        OpeningHoursUpdateInput bodyInput = new OpeningHoursUpdateInput(
                bodyIdDifferent,
                restaurantId,
                DayOfWeek.SATURDAY,
                LocalTime.of(11, 0),
                LocalTime.of(23, 0),
                true
        );

        OpeningHoursDto expected = new OpeningHoursDto(
                pathId,
                restaurantId,
                DayOfWeek.SATURDAY,
                LocalTime.of(11, 0),
                LocalTime.of(23, 0),
                true
        );

        when(updateOpeningHours.execute(any(OpeningHoursUpdateInput.class))).thenReturn(expected);

        ResponseEntity<OpeningHoursDto> response = openingHoursController.update(pathId, bodyInput);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isEqualTo(pathId); // id do path prevalece
        assertThat(response.getBody().restaurantId()).isEqualTo(restaurantId);
        assertThat(response.getBody().isClosed()).isTrue();

        verify(updateOpeningHours).execute(any(OpeningHoursUpdateInput.class));
    }
}
