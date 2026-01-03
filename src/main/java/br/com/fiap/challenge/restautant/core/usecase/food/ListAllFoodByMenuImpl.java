package br.com.fiap.challenge.restautant.core.usecase.food;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.gateway.FoodGateway;

public class ListAllFoodByMenuImpl implements ListAllFoodByMenu {

    private final FoodGateway foodGateway;

    public ListAllFoodByMenuImpl(FoodGateway foodGateway) {
        this.foodGateway = foodGateway;
    }

    @Override
    public List<FoodDto> execute(UUID menuId) {
        return foodGateway.getFoodsByMenuId(menuId);
    }

}