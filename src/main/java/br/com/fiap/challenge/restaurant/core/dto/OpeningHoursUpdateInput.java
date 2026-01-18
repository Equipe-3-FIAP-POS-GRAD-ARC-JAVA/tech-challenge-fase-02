package br.com.fiap.challenge.restaurant.core.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public record OpeningHoursUpdateInput(UUID id,
                                      UUID restaurantId,
                                      DayOfWeek weekDay,
                                      LocalTime opensAt,
                                      LocalTime closesAt,
                                      boolean isClosed) {

}