package br.com.fiap.challenge.restautant.core.usecase.food;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import br.com.fiap.challenge.restautant.core.usecase.base.UseCase;

public interface CreatedFood extends UseCase<FoodInput, FoodDto> {

    FoodDto execute(FoodInput foodInput);

}
