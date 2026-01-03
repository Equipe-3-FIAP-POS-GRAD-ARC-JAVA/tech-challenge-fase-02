package br.com.fiap.challenge.restautant.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.challenge.restautant.core.gateway.FoodGateway;
import br.com.fiap.challenge.restautant.core.usecase.food.CreatedFood;
import br.com.fiap.challenge.restautant.core.usecase.food.CreatedFoodImpl;
import br.com.fiap.challenge.restautant.core.usecase.food.DeleteFood;
import br.com.fiap.challenge.restautant.core.usecase.food.DeleteFoodImpl;
import br.com.fiap.challenge.restautant.core.usecase.food.ListAllFoodByMenu;
import br.com.fiap.challenge.restautant.core.usecase.food.ListAllFoodByMenuImpl;
import br.com.fiap.challenge.restautant.core.usecase.food.ListFoodById;
import br.com.fiap.challenge.restautant.core.usecase.food.ListFoodByIdImpl;
import br.com.fiap.challenge.restautant.core.usecase.food.UpdateFood;
import br.com.fiap.challenge.restautant.core.usecase.food.UpdateFoodImpl;

@Configuration
public class UseCaseFoodConfig {

    @Bean
    public CreatedFood createdFood(FoodGateway foodGateway) {
        return new CreatedFoodImpl(foodGateway);
    }

    @Bean
    public DeleteFood deleteFood(FoodGateway foodGateway) {
        return new DeleteFoodImpl(foodGateway);
    }

    @Bean
    public ListAllFoodByMenu listAllFoodByMenu(FoodGateway foodGateway) {
        return new ListAllFoodByMenuImpl(foodGateway);
    }

    @Bean
    public ListFoodById listFoodById(FoodGateway foodGateway) {
        return new ListFoodByIdImpl(foodGateway);
    }

    @Bean
    public UpdateFood updateFood(FoodGateway foodGateway) {
        return new UpdateFoodImpl(foodGateway);
    }
}