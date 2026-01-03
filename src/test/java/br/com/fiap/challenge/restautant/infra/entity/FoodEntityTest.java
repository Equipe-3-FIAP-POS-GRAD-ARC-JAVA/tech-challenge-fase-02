package br.com.fiap.challenge.restautant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodEntityTest {

    @Test
    @DisplayName("Should create Food with all parameters")
    void shouldCreateFoodWithAllParameters() {
        // Given
        Restaurant restaurant = new Restaurant("Restaurant Test",
            new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        Menu menu = new Menu(restaurant, new ArrayList<>());
        FoodType foodType = new FoodType("Italian");
        String name = "Pizza Margherita";
        String description = "Classic Italian pizza";
        BigDecimal price = BigDecimal.valueOf(35.50);
        String imageUrl = "http://image.url/pizza.jpg";
        
        // When
        Food food = new Food(name, description, foodType, price, imageUrl, menu);
        
        // Then
        assertThat(food.getName()).isEqualTo(name);
        assertThat(food.getDescription()).isEqualTo(description);
        assertThat(food.getFoodType()).isEqualTo(foodType);
        assertThat(food.getPrice()).isEqualTo(price);
        assertThat(food.getImageUrl()).isEqualTo(imageUrl);
        assertThat(food.getMenu()).isEqualTo(menu);
    }

    @Test
    @DisplayName("Should update food name")
    void shouldUpdateFoodName() {
        // Given
        Food food = createSampleFood();
        
        // When
        food.setName("New Pizza Name");
        
        // Then
        assertThat(food.getName()).isEqualTo("New Pizza Name");
    }

    @Test
    @DisplayName("Should update food description")
    void shouldUpdateFoodDescription() {
        // Given
        Food food = createSampleFood();
        
        // When
        food.setDescription("New delicious description");
        
        // Then
        assertThat(food.getDescription()).isEqualTo("New delicious description");
    }

    @Test
    @DisplayName("Should update food type")
    void shouldUpdateFoodType() {
        // Given
        Food food = createSampleFood();
        FoodType newType = new FoodType("Brazilian");
        
        // When
        food.setFoodType(newType);
        
        // Then
        assertThat(food.getFoodType()).isEqualTo(newType);
        assertThat(food.getFoodType().getTypeFood()).isEqualTo("Brazilian");
    }

    @Test
    @DisplayName("Should update food price")
    void shouldUpdateFoodPrice() {
        // Given
        Food food = createSampleFood();
        BigDecimal newPrice = BigDecimal.valueOf(45.00);
        
        // When
        food.setPrice(newPrice);
        
        // Then
        assertThat(food.getPrice()).isEqualTo(newPrice);
    }

    @Test
    @DisplayName("Should update image url")
    void shouldUpdateImageUrl() {
        // Given
        Food food = createSampleFood();
        String newImageUrl = "http://newimage.url/food.jpg";
        
        // When
        food.setImageUrl(newImageUrl);
        
        // Then
        assertThat(food.getImageUrl()).isEqualTo(newImageUrl);
    }

    @Test
    @DisplayName("Should update menu")
    void shouldUpdateMenu() {
        // Given
        Food food = createSampleFood();
        Restaurant newRestaurant = new Restaurant("New Restaurant",
            new Address("Rua Nova", "456", "Rio de Janeiro", "Copacabana", "Brasil",
                new State("RJ"), new ZipCode("98765-432")));
        Menu newMenu = new Menu(newRestaurant, new ArrayList<>());
        
        // When
        food.setMenu(newMenu);
        
        // Then
        assertThat(food.getMenu()).isEqualTo(newMenu);
        assertThat(food.getMenu().getRestaurant().getName()).isEqualTo("New Restaurant");
    }

    private Food createSampleFood() {
        Restaurant restaurant = new Restaurant("Restaurant Test",
            new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil",
                new State("SP"), new ZipCode("12345-678")));
        Menu menu = new Menu(restaurant, new ArrayList<>());
        FoodType foodType = new FoodType("Italian");
        return new Food("Pizza", "Delicious pizza", foodType,
            BigDecimal.valueOf(35.50), "http://image.url", menu);
    }
}
