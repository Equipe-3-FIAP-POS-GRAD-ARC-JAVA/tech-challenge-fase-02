package br.com.fiap.challenge.restautant.core.usecase.food;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import br.com.fiap.challenge.restautant.core.gateway.FoodGateway;

public class UpdateFoodImpl implements UpdateFood {

    private final FoodGateway foodGateway;

    public UpdateFoodImpl(FoodGateway foodGateway) {
        this.foodGateway = foodGateway;
    }

    @Override
    public FoodDto execute(FoodInput foodInput) {
        return foodGateway.updateFood(foodInput);
    }

}