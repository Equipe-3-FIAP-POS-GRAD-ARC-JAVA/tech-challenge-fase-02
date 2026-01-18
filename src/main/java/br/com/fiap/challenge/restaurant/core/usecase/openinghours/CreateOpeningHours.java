package br.com.fiap.challenge.restaurant.core.usecase.openinghours;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface CreateOpeningHours extends UseCase<OpeningHoursInput, OpeningHoursDto> {
    OpeningHoursDto execute(OpeningHoursInput input);
}
