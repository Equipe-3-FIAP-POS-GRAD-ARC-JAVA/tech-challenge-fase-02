package br.com.fiap.challenge.restaurant.core.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public record OpeningHoursDto(UUID id,
                              UUID restaurantId,
                              DayOfWeek weekday,
                              LocalTime opensAt,
                              LocalTime closesAt,
                              boolean isClosed) {
}
