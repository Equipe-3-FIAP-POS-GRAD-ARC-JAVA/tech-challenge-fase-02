package br.com.fiap.challenge.restautant.core.dto;

import java.util.UUID;

public record RestaurantInput(UUID id, String name, AddressInput address) {

}

