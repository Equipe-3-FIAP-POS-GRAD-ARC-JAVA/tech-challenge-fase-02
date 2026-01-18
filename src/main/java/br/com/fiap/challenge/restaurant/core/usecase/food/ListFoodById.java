package br.com.fiap.challenge.restaurant.core.usecase.food;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.FoodDto;
import br.com.fiap.challenge.restaurant.core.usecase.base.UseCase;

public interface ListFoodById extends UseCase<UUID, FoodDto> {
    FoodDto execute(UUID foodId);
}
