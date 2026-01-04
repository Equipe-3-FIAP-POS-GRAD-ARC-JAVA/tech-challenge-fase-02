package br.com.fiap.challenge.restaurant.core.usecase.openinghours;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;
import br.com.fiap.challenge.restaurant.core.gateway.OpeningHoursGateway;

public class UpdateOpeningHoursImpl implements UpdateOpeningHours {

    private final OpeningHoursGateway openingHoursGateway;

    public UpdateOpeningHoursImpl(OpeningHoursGateway openingHoursGateway) {
        this.openingHoursGateway = openingHoursGateway;
    }

    @Override
    public OpeningHoursDto execute(OpeningHoursUpdateInput input) {
        return openingHoursGateway.updateOpeningHours(input);
    }
}
