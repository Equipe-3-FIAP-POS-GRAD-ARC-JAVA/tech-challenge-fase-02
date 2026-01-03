package br.com.fiap.challenge.restautant.core.usecase.food;

import java.util.UUID;

import br.com.fiap.challenge.restautant.core.gateway.FoodGateway;

public class DeleteFoodImpl implements DeleteFood {

    private final FoodGateway foodGateway;

    public DeleteFoodImpl(FoodGateway foodGateway) {
        this.foodGateway = foodGateway;
    }

    @Override
    public void execute(UUID foodId) {
        foodGateway.deleteFood(foodId);
    }

}