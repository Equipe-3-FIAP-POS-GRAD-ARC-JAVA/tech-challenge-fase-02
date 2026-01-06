package br.com.fiap.challenge.restaurant.core.domain.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OpeningHours extends BaseEntity {

    private Restaurant restaurant;
    private DayOfWeek weekday;
    private LocalTime opensAt;
    private LocalTime closesAt;
    private boolean isClosed;
}
