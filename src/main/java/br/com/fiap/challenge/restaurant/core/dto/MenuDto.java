package br.com.fiap.challenge.restaurant.core.dto;

import java.util.List;
import java.util.UUID;

public record MenuDto(UUID menuId, UUID restaurantId, List<FoodDto> foods) {

}
