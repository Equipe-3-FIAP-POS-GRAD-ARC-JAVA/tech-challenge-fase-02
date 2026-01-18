package br.com.fiap.challenge.restaurant.core.usecase.openinghours;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.gateway.OpeningHoursGateway;

public class CreateOpeningHoursImpl implements CreateOpeningHours {

    private final OpeningHoursGateway openingHoursGateway;

    public CreateOpeningHoursImpl(OpeningHoursGateway openingHoursGateway) {
        this.openingHoursGateway = openingHoursGateway;
    }

    @Override
    public OpeningHoursDto execute(OpeningHoursInput input) {
        return openingHoursGateway.createOpeningHours(input);
    }
}
