package br.com.fiap.challenge.restaurant.core.usecase.openinghours;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface UpdateOpeningHours extends UseCase<OpeningHoursUpdateInput, OpeningHoursDto> {
    OpeningHoursDto execute(OpeningHoursUpdateInput input);
}
