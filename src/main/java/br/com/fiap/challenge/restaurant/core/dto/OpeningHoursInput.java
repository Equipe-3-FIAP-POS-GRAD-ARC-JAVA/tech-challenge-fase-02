package br.com.fiap.challenge.restaurant.core.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public record OpeningHoursInput(UUID restaurantId,
                                DayOfWeek weekday,
                                LocalTime opensAt,
                                LocalTime closesAt,
                                boolean isClosed) {
}
