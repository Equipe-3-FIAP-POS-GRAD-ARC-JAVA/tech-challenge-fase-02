package br.com.fiap.challenge.restaurant.core.usecase.food;

import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.dto.FoodDto;
import br.com.fiap.challenge.restaurant.core.gateway.FoodGateway;

public class ListFoodByIdImpl implements ListFoodById {

    private final FoodGateway foodGateway;

    public ListFoodByIdImpl(FoodGateway foodGateway) {
        this.foodGateway = foodGateway;
    }

    @Override
    public FoodDto execute(UUID foodId) {
        return foodGateway.getFoodById(foodId);
    }

}