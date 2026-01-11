package br.com.fiap.challenge.restaurant.core.dto;

import java.util.UUID;

public record OwnerRestaurantDto(
        UUID id,
        UUID ownerId,
        UUID restaurantId
) {}
