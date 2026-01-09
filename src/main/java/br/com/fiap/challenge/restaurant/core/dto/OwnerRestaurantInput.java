package br.com.fiap.challenge.restaurant.core.dto;

import java.util.UUID;

public record OwnerRestaurantInput(
        UUID ownerId,
        UUID restaurantId
) {}
