package br.com.fiap.challenge.restautant.core.usecase.food;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.usecase.UseCase;

public interface ListFoodById extends UseCase<UUID, FoodDto> {
    FoodDto execute(UUID foodId);
}
