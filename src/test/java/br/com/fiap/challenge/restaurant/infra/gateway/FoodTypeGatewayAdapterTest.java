package br.com.fiap.challenge.restaurant.infra.gateway;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restaurant.infra.entity.FoodType;
import br.com.fiap.challenge.restaurant.infra.repository.FoodTypeRepository;

@ExtendWith(MockitoExtension.class)
class FoodTypeGatewayAdapterTest {

    @Mock
    private FoodTypeRepository foodTypeRepository;

    private FoodTypeGatewayAdapter gateway;

    @BeforeEach
    void setUp() {
        gateway = new FoodTypeGatewayAdapter(foodTypeRepository);
    }

    @Test
    @DisplayName("Should get all food types")
    void shouldGetAllFoodTypes() {
        UUID foodTypeId = UUID.randomUUID();
        FoodType foodType = new FoodType("Pizza");
        foodType.setId(foodTypeId);

        when(foodTypeRepository.findAll()).thenReturn(List.of(foodType));

        List<FoodTypeDto> result = gateway.getAllFoodTypes();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).fooTypeId()).isEqualTo(foodTypeId);
        assertThat(result.get(0).typeFood()).isEqualTo("Pizza");
    }

    @Test
    @DisplayName("Should get food type by id")
    void shouldGetFoodTypeById() {
        UUID foodTypeId = UUID.randomUUID();
        FoodType foodType = new FoodType("Pizza");
        foodType.setId(foodTypeId);

        when(foodTypeRepository.findById(foodTypeId)).thenReturn(Optional.of(foodType));

        FoodTypeDto result = gateway.getFoodTypeById(foodTypeId);

        assertThat(result).isNotNull();
        assertThat(result.fooTypeId()).isEqualTo(foodTypeId);
        assertThat(result.typeFood()).isEqualTo("Pizza");
    }

    @Test
    @DisplayName("Should return null when food type not found")
    void shouldReturnNullWhenFoodTypeNotFound() {
        UUID foodTypeId = UUID.randomUUID();

        when(foodTypeRepository.findById(foodTypeId)).thenReturn(Optional.empty());

        FoodTypeDto result = gateway.getFoodTypeById(foodTypeId);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Should create food type")
    void shouldCreateFoodType() {
        UUID foodTypeId = UUID.randomUUID();
        FoodTypeInput input = new FoodTypeInput(null, "Pizza");
        FoodType foodType = new FoodType("Pizza");
        foodType.setId(foodTypeId);

        when(foodTypeRepository.save(any(FoodType.class))).thenReturn(foodType);

        FoodTypeDto result = gateway.createFoodType(input);

        assertThat(result).isNotNull();
        assertThat(result.fooTypeId()).isEqualTo(foodTypeId);
        assertThat(result.typeFood()).isEqualTo("Pizza");
    }

    @Test
    @DisplayName("Should update food type")
    void shouldUpdateFoodType() {
        UUID foodTypeId = UUID.randomUUID();
        FoodTypeInput input = new FoodTypeInput(foodTypeId, "Updated Pizza");
        FoodType existingFoodType = new FoodType("Pizza");
        existingFoodType.setId(foodTypeId);
        FoodType updatedFoodType = new FoodType("Updated Pizza");
        updatedFoodType.setId(foodTypeId);

        when(foodTypeRepository.findById(foodTypeId)).thenReturn(Optional.of(existingFoodType));
        when(foodTypeRepository.save(any(FoodType.class))).thenReturn(updatedFoodType);

        FoodTypeDto result = gateway.updateFoodType(input);

        assertThat(result).isNotNull();
        assertThat(result.fooTypeId()).isEqualTo(foodTypeId);
        assertThat(result.typeFood()).isEqualTo("Updated Pizza");
    }

    @Test
    @DisplayName("Should delete food type")
    void shouldDeleteFoodType() {
        UUID foodTypeId = UUID.randomUUID();

        gateway.deleteFoodType(foodTypeId);

        verify(foodTypeRepository).deleteById(foodTypeId);
    }
}