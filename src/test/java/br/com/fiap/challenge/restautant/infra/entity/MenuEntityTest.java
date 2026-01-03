package br.com.fiap.challenge.restautant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuEntityTest {

    @Test
    @DisplayName("Should create Menu with restaurant and foods")
    void shouldCreateMenuWithRestaurantAndFoods() {
        // Given
        Restaurant restaurant = new Restaurant("Restaurant Test",
            new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        List<Food> foods = new ArrayList<>();
        
        // When
        Menu menu = new Menu(restaurant, foods);
        
        // Then
        assertThat(menu.getRestaurant()).isEqualTo(restaurant);
        assertThat(menu.getFoods()).isEqualTo(foods);
    }

    @Test
    @DisplayName("Should update restaurant")
    void shouldUpdateRestaurant() {
        // Given
        Restaurant oldRestaurant = new Restaurant("Old Restaurant",
            new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        Menu menu = new Menu(oldRestaurant, new ArrayList<>());
        Restaurant newRestaurant = new Restaurant("New Restaurant",
            new Address("Rua Nova", "456", "Rio de Janeiro", "Copacabana", "Brasil",
                new State("RJ"), new ZipCode("98765-432")));
        
        // When
        menu.setRestaurant(newRestaurant);
        
        // Then
        assertThat(menu.getRestaurant()).isEqualTo(newRestaurant);
        assertThat(menu.getRestaurant().getName()).isEqualTo("New Restaurant");
    }

    @Test
    @DisplayName("Should update foods list")
    void shouldUpdateFoodsList() {
        // Given
        Restaurant restaurant = new Restaurant("Restaurant Test",
            new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        Menu menu = new Menu(restaurant, new ArrayList<>());
        List<Food> newFoods = new ArrayList<>();
        Food food = new Food("Pizza", "Delicious pizza", new FoodType("Italian"),
            java.math.BigDecimal.valueOf(25.50), "http://image.url", menu);
        newFoods.add(food);
        
        // When
        menu.setFoods(newFoods);
        
        // Then
        assertThat(menu.getFoods()).hasSize(1);
        assertThat(menu.getFoods().get(0).getName()).isEqualTo("Pizza");
    }
}
