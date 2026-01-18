package br.com.fiap.challenge.restaurant.core.usecase.openinghours;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;

public interface ListOpeningHoursByRestaurant {
    List<OpeningHoursDto> execute(UUID restaurantId);
}
