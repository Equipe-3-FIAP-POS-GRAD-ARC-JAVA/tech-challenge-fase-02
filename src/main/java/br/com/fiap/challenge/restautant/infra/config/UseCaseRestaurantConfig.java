package br.com.fiap.challenge.restautant.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.challenge.restautant.core.gateway.RestaurantGateway;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.CreateRestaurant;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.CreateRestaurantImpl;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.DeleteRestaurant;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.DeleteRestaurantImpl;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.ListAllRestaurant;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.ListAllRestaurantImpl;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.ListRestaurantById;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.ListRestaurantByIdImpl;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.UpdateRestaurant;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.UpdateRestaurantImpl;

@Configuration
public class UseCaseRestaurantConfig {

    @Bean
    public CreateRestaurant createRestaurant(RestaurantGateway restaurantGateway) {
        return new CreateRestaurantImpl(restaurantGateway);
    }

    @Bean
    public DeleteRestaurant deleteRestaurant(RestaurantGateway restaurantGateway) {
        return new DeleteRestaurantImpl(restaurantGateway);
    }

    @Bean
    public ListAllRestaurant listAllRestaurant(RestaurantGateway restaurantGateway) {
        return new ListAllRestaurantImpl(restaurantGateway);
    }

    @Bean
    public ListRestaurantById listRestaurantById(RestaurantGateway restaurantGateway) {
        return new ListRestaurantByIdImpl(restaurantGateway);
    }

    @Bean
    public UpdateRestaurant updateRestaurant(RestaurantGateway restaurantGateway) {
        return new UpdateRestaurantImpl(restaurantGateway);
    }
}