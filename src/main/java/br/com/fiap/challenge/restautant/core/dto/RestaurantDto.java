package br.com.fiap.challenge.restautant.core.dto;

import java.util.UUID;

public record RestaurantDto(UUID restaurantId, String name, MenuDto menu) {

}

