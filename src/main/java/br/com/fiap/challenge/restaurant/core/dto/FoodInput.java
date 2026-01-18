package br.com.fiap.challenge.restaurant.core.dto;

import java.util.UUID;
public record FoodInput(UUID id, UUID menuId, String name, String description, UUID foodTypeId, Double price, String imageURL) {

}
