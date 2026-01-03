package br.com.fiap.challenge.restautant.infra.web.controller;

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.dto.FoodTypeInput;
import br.com.fiap.challenge.restautant.core.usecase.foodType.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/food-types")
public class FoodTypeController {

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
        FoodTypeInput updatedInput = new FoodTypeInput(id, input.name());
        FoodTypeDto result = updateFoodType.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteFoodType.execute(id);
        return ResponseEntity.noContent().build();
    }
}