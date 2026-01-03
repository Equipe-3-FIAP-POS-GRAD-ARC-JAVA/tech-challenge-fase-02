package br.com.fiap.challenge.restautant.core.usecase.food;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.usecase.base.UseCase;

public interface ListAllFoodByMenu extends UseCase<UUID, List<FoodDto>> {
    List<FoodDto> execute(UUID menuId);
}
