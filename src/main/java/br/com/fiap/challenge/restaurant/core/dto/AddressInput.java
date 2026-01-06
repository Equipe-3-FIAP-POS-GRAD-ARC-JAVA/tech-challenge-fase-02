package br.com.fiap.challenge.restaurant.core.dto;

public record AddressInput(
    String street,
    String number,
    String city,
    String neighborhood,
    String country,
    String state,
    String zipCode) {
}
