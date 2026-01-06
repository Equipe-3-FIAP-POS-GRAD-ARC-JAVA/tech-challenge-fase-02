package br.com.fiap.challenge.restaurant.infra.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.challenge.restaurant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restaurant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.CreateFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.DeleteFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.ListAllFoodType;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.ListFoodTypeById;
import br.com.fiap.challenge.restaurant.core.usecase.foodType.UpdateFoodType;
import br.com.fiap.challenge.restaurant.infra.web.api.FoodTypeAPI;

@RestController
@RequestMapping("/api/food-types")
public class FoodTypeController implements FoodTypeAPI {

    private final CreateFoodType createFoodType;
    private final ListAllFoodType listAllFoodType;
    private final ListFoodTypeById listFoodTypeById;
    private final UpdateFoodType updateFoodType;
    private final DeleteFoodType deleteFoodType;

    public FoodTypeController(CreateFoodType createFoodType, ListAllFoodType listAllFoodType,
                              ListFoodTypeById listFoodTypeById, UpdateFoodType updateFoodType,
                              DeleteFoodType deleteFoodType) {
        this.createFoodType = createFoodType;
        this.listAllFoodType = listAllFoodType;
        this.listFoodTypeById = listFoodTypeById;
        this.updateFoodType = updateFoodType;
        this.deleteFoodType = deleteFoodType;
    }

    @PostMapping
    public ResponseEntity<FoodTypeDto> create(@RequestBody FoodTypeInput input) {
        FoodTypeDto result = createFoodType.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<FoodTypeDto>> listAll() {
        List<FoodTypeDto> result = listAllFoodType.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodTypeDto> getById(@PathVariable UUID id) {
        FoodTypeDto result = listFoodTypeById.execute(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodTypeDto> update(@PathVariable UUID id, @RequestBody FoodTypeInput input) {
        FoodTypeInput updatedInput = new FoodTypeInput(id, input.typeFood());
        FoodTypeDto result = updateFoodType.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteFoodType.execute(id);
        return ResponseEntity.noContent().build();
    }
}