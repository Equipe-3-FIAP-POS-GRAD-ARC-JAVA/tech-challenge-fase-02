package br.com.fiap.challenge.restautant.infra.web.controller;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import br.com.fiap.challenge.restautant.core.usecase.food.CreatedFood;
import br.com.fiap.challenge.restautant.core.usecase.food.DeleteFood;
import br.com.fiap.challenge.restautant.core.usecase.food.ListAllFood;
import br.com.fiap.challenge.restautant.core.usecase.food.ListAllFoodByMenu;
import br.com.fiap.challenge.restautant.core.usecase.food.ListFoodById;
import br.com.fiap.challenge.restautant.core.usecase.food.UpdateFood;

@ExtendWith(MockitoExtension.class)
class FoodControllerTest {

    @InjectMocks
    private FoodController foodController;

    @Mock
    private CreatedFood createdFood;

    @Mock
    private ListAllFood listAllFood;

    @Mock
    private ListFoodById listFoodById;

    @Mock
    private ListAllFoodByMenu listAllFoodByMenu;

    @Mock
    private UpdateFood updateFood;

    @Mock
    private DeleteFood deleteFood;

    @Test
    @DisplayName("Should create food successfully")
    void shouldCreateFood() {
        UUID foodId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        FoodInput input = new FoodInput(null, menuId, "Pizza", "Delicious pizza", foodTypeId, 25.99, "http://image.com");
        FoodDto expected = new FoodDto(menuId, foodId, "Pizza", "Delicious pizza", foodTypeId, 25.99, "http://image.com");

        when(createdFood.execute(any(FoodInput.class))).thenReturn(expected);

        ResponseEntity<FoodDto> response = foodController.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().foodId()).isEqualTo(foodId);
        verify(createdFood).execute(any(FoodInput.class));
    }

    @Test
    @DisplayName("Should list all foods successfully")
    void shouldListAllFoods() {
        UUID foodId = UUID.randomUUID();
        FoodDto food = new FoodDto(UUID.randomUUID(), foodId, "Pizza", "Delicious pizza", UUID.randomUUID(), 25.99, "http://image.com");
        List<FoodDto> foods = List.of(food);

        when(listAllFood.execute()).thenReturn(foods);

        ResponseEntity<List<FoodDto>> response = foodController.listAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(1);
        assertThat(response.getBody().get(0).foodId()).isEqualTo(foodId);
        verify(listAllFood).execute();
    }

    @Test
    @DisplayName("Should get food by id successfully")
    void shouldGetFoodById() {
        UUID foodId = UUID.randomUUID();
        FoodDto food = new FoodDto(UUID.randomUUID(), foodId, "Pizza", "Delicious pizza", UUID.randomUUID(), 25.99, "http://image.com");

        when(listFoodById.execute(foodId)).thenReturn(food);

        ResponseEntity<FoodDto> response = foodController.getById(foodId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().foodId()).isEqualTo(foodId);
        verify(listFoodById).execute(foodId);
    }

    @Test
    @DisplayName("Should return not found when food does not exist")
    void shouldReturnNotFoundWhenFoodNotExists() {
        UUID foodId = UUID.randomUUID();

        when(listFoodById.execute(foodId)).thenReturn(null);

        ResponseEntity<FoodDto> response = foodController.getById(foodId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(listFoodById).execute(foodId);
    }

    @Test
    @DisplayName("Should get foods by menu successfully")
    void shouldGetFoodsByMenu() {
        UUID menuId = UUID.randomUUID();
        UUID foodId = UUID.randomUUID();
        FoodDto food = new FoodDto(menuId, foodId, "Pizza", "Delicious pizza", UUID.randomUUID(), 25.99, "http://image.com");
        List<FoodDto> foods = List.of(food);

        when(listAllFoodByMenu.execute(menuId)).thenReturn(foods);

        ResponseEntity<List<FoodDto>> response = foodController.getByMenu(menuId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(1);
        assertThat(response.getBody().get(0).foodId()).isEqualTo(foodId);
        verify(listAllFoodByMenu).execute(menuId);
    }

    @Test
    @DisplayName("Should update food successfully")
    void shouldUpdateFood() {
        UUID foodId = UUID.randomUUID();
        UUID menuId = UUID.randomUUID();
        UUID foodTypeId = UUID.randomUUID();
        FoodInput input = new FoodInput(null, menuId, "Updated Pizza", "Updated description", foodTypeId, 30.00, "http://updated.com");
        FoodDto expected = new FoodDto(menuId, foodId, "Updated Pizza", "Updated description", foodTypeId, 30.00, "http://updated.com");

        when(updateFood.execute(any(FoodInput.class))).thenReturn(expected);

        ResponseEntity<FoodDto> response = foodController.update(foodId, input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().name()).isEqualTo("Updated Pizza");
        verify(updateFood).execute(any(FoodInput.class));
    }

    @Test
    @DisplayName("Should delete food successfully")
    void shouldDeleteFood() {
        UUID foodId = UUID.randomUUID();

        doNothing().when(deleteFood).execute(foodId);

        ResponseEntity<Void> response = foodController.delete(foodId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
        verify(deleteFood).execute(foodId);
    }
}