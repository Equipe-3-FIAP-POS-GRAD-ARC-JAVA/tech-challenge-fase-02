package br.com.fiap.challenge.restautant.core.domain.entity;

import java.util.UUID;

public class Food extends BaseEntity {
    private String name;
    private String description;
    private FoodType foodType;
    private Double price;
    private String imageURL;
    private UUID menu_id;
}
