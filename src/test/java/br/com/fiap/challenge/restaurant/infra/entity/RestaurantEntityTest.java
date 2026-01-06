package br.com.fiap.challenge.restaurant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestaurantEntityTest {

    @Test
    @DisplayName("Should create Restaurant with name and address")
    void shouldCreateRestaurantWithNameAndAddress() {
        // Given
        String name = "Restaurant Test";
        Address address = new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
            new State("SP"), new ZipCode("12345-678"));
        
        // When
        Restaurant restaurant = new Restaurant(name, address);
        
        // Then
        assertThat(restaurant.getName()).isEqualTo(name);
        assertThat(restaurant.getAddress()).isEqualTo(address);
    }

    @Test
    @DisplayName("Should update restaurant name")
    void shouldUpdateRestaurantName() {
        // Given
        Restaurant restaurant = new Restaurant("Old Name", 
            new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        
        // When
        restaurant.setName("New Name");
        
        // Then
        assertThat(restaurant.getName()).isEqualTo("New Name");
    }

    @Test
    @DisplayName("Should update restaurant address")
    void shouldUpdateRestaurantAddress() {
        // Given
        Restaurant restaurant = new Restaurant("Restaurant Test",
            new Address("Rua Antiga", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        Address newAddress = new Address("Rua Nova", "456", "Rio de Janeiro", "Copacabana", "Brasil",
            new State("RJ"), new ZipCode("98765-432"));
        
        // When
        restaurant.setAddress(newAddress);
        
        // Then
        assertThat(restaurant.getAddress()).isEqualTo(newAddress);
        assertThat(restaurant.getAddress().getStreet()).isEqualTo("Rua Nova");
    }

    @Test
    @DisplayName("Should set and get menus")
    void shouldSetAndGetMenus() {
        // Given
        Restaurant restaurant = new Restaurant("Restaurant Test",
            new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu(restaurant, new ArrayList<>());
        menus.add(menu);
        
        // When
        restaurant.setMenus(menus);
        
        // Then
        assertThat(restaurant.getMenus()).isNotNull();
        assertThat(restaurant.getMenus()).hasSize(1);
        assertThat(restaurant.getMenus().get(0)).isEqualTo(menu);
    }

    @Test
    @DisplayName("Should return null menus when not set")
    void shouldReturnNullMenusWhenNotSet() {
        // Given
        Restaurant restaurant = new Restaurant("Restaurant Test",
            new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        
        // When & Then
        assertThat(restaurant.getMenus()).isNull();
    }
}
