package br.com.fiap.challenge.restaurant.infra.web.controller;

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

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.CreateFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.DeleteFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.ListAllFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.ListFoodTypeById;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.UpdateFoodType;

@ExtendWith(MockitoExtension.class)
class FoodTypeControllerTest {

    @InjectMocks
    private FoodTypeController foodTypeController;

    @Mock
    private CreateFoodType createFoodType;

    @Mock
    private ListAllFoodType listAllFoodType;

    @Mock
    private ListFoodTypeById listFoodTypeById;

    @Mock
    private UpdateFoodType updateFoodType;

    @Mock
    private DeleteFoodType deleteFoodType;

    @Test
    @DisplayName("Should create food type successfully")
    void shouldCreateFoodType() {
        UUID foodTypeId = UUID.randomUUID();
        FoodTypeInput input = new FoodTypeInput(null, "Pizza");
        FoodTypeDto expected = new FoodTypeDto(foodTypeId, "Pizza");

        when(createFoodType.execute(any(FoodTypeInput.class))).thenReturn(expected);

        ResponseEntity<FoodTypeDto> response = foodTypeController.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().fooTypeId()).isEqualTo(foodTypeId);
        assertThat(response.getBody().typeFood()).isEqualTo("Pizza");
        verify(createFoodType).execute(any(FoodTypeInput.class));
    }

    @Test
    @DisplayName("Should list all food types successfully")
    void shouldListAllFoodTypes() {
        UUID foodTypeId = UUID.randomUUID();
        FoodTypeDto dto = new FoodTypeDto(foodTypeId, "Pizza");

        when(listAllFoodType.execute()).thenReturn(List.of(dto));

        ResponseEntity<List<FoodTypeDto>> response = foodTypeController.listAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(1);
        assertThat(response.getBody().get(0).fooTypeId()).isEqualTo(foodTypeId);
        assertThat(response.getBody().get(0).typeFood()).isEqualTo("Pizza");
        verify(listAllFoodType).execute();
    }

    @Test
    @DisplayName("Should get food type by id successfully")
    void shouldGetFoodTypeById() {
        UUID foodTypeId = UUID.randomUUID();
        FoodTypeDto dto = new FoodTypeDto(foodTypeId, "Pizza");

        when(listFoodTypeById.execute(foodTypeId)).thenReturn(dto);

        ResponseEntity<FoodTypeDto> response = foodTypeController.getById(foodTypeId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().fooTypeId()).isEqualTo(foodTypeId);
        assertThat(response.getBody().typeFood()).isEqualTo("Pizza");
        verify(listFoodTypeById).execute(foodTypeId);
    }

    @Test
    @DisplayName("Should return not found when food type does not exist")
    void shouldReturnNotFoundWhenFoodTypeDoesNotExist() {
        UUID foodTypeId = UUID.randomUUID();

        when(listFoodTypeById.execute(foodTypeId)).thenReturn(null);

        ResponseEntity<FoodTypeDto> response = foodTypeController.getById(foodTypeId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(listFoodTypeById).execute(foodTypeId);
    }

    @Test
    @DisplayName("Should update food type successfully")
    void shouldUpdateFoodType() {
        UUID foodTypeId = UUID.randomUUID();
        FoodTypeInput input = new FoodTypeInput(null, "Updated Pizza");
        FoodTypeDto expected = new FoodTypeDto(foodTypeId, "Updated Pizza");

        when(updateFoodType.execute(any(FoodTypeInput.class))).thenReturn(expected);

        ResponseEntity<FoodTypeDto> response = foodTypeController.update(foodTypeId, input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().fooTypeId()).isEqualTo(foodTypeId);
        assertThat(response.getBody().typeFood()).isEqualTo("Updated Pizza");
        verify(updateFoodType).execute(any(FoodTypeInput.class));
    }

    @Test
    @DisplayName("Should delete food type successfully")
    void shouldDeleteFoodType() {
        UUID foodTypeId = UUID.randomUUID();

        doNothing().when(deleteFoodType).execute(foodTypeId);

        ResponseEntity<Void> response = foodTypeController.delete(foodTypeId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
        verify(deleteFoodType).execute(foodTypeId);
    }
}