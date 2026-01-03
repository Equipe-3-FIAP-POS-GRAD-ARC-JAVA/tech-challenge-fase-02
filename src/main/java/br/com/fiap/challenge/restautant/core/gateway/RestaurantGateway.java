package br.com.fiap.challenge.restautant.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;

public interface RestaurantGateway {

    List<RestaurantDto> getAllRestaurants();
    RestaurantDto getRestaurantById(UUID restaurantId);
    RestaurantDto createRestaurant(RestaurantInput restaurant);
    RestaurantDto updateRestaurant(RestaurantInput restaurant);
    void deleteRestaurant(UUID restaurantId);
    List<String> getTypesFoods();
}
