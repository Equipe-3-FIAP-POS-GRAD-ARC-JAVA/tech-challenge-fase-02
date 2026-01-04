package br.com.fiap.challenge.restaurant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;

public interface OpeningHoursGateway {

    OpeningHoursDto createOpeningHours(OpeningHoursInput input);

    OpeningHoursDto updateOpeningHours(OpeningHoursUpdateInput input);

    List<OpeningHoursDto> listOpeningHoursByRestaurant(UUID restaurantId);
}
