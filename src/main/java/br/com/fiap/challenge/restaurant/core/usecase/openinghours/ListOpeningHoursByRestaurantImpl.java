package br.com.fiap.challenge.restaurant.core.usecase.openinghours;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.gateway.OpeningHoursGateway;

public class ListOpeningHoursByRestaurantImpl implements ListOpeningHoursByRestaurant {

    private final OpeningHoursGateway openingHoursGateway;

    public ListOpeningHoursByRestaurantImpl(OpeningHoursGateway openingHoursGateway) {
        this.openingHoursGateway = openingHoursGateway;
    }

    @Override
    public List<OpeningHoursDto> execute(UUID restaurantId) {
        return openingHoursGateway.listOpeningHoursByRestaurant(restaurantId);
    }
}
