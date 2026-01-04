package br.com.fiap.challenge.restaurant.core.usecase.food;

import br.com.fiap.challenge.restaurant.core.dto.FoodDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodInput;
import br.com.fiap.challenge.restaurant.core.gateway.FoodGateway;

public class CreatedFoodImpl implements CreatedFood {

    private final FoodGateway foodGateway;

    public CreatedFoodImpl(FoodGateway foodGateway) {
        this.foodGateway = foodGateway;
    }

    @Override
    public FoodDto execute(FoodInput foodInput) {
        return foodGateway.createFood(foodInput);
    }

}