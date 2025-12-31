package br.com.fiap.challenge.restautant.core.dto;

import java.util.UUID;

public record FoodDto(UUID menuId, UUID foodId, String name, String description, UUID foodTypeId, Double price, String imageURL) {

}
