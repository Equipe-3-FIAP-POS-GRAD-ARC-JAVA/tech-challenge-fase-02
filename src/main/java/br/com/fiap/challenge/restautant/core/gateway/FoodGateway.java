package br.com.fiap.challenge.restautant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;

public interface FoodGateway {

    List<FoodDto> getAllFoods();

    FoodDto getFoodById(UUID foodId);

    List<FoodDto> getFoodsByMenuId(UUID menuId);

    FoodDto createFood(FoodInput food);

    FoodDto updateFood(FoodInput food);

    void deleteFood(UUID foodId);

}
