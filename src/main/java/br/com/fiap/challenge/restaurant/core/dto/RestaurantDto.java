package br.com.fiap.challenge.restaurant.core.dto;

import java.util.List;
import java.util.UUID;

public record RestaurantDto(UUID restaurantId, String name, List<MenuDto> menus) {

}

