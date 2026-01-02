package br.com.fiap.challenge.restautant.core.dto;

import java.util.UUID;
public record FoodInput(UUID menuId, String name, String description, UUID foodTypeId, Double price, String imageURL) {

}
