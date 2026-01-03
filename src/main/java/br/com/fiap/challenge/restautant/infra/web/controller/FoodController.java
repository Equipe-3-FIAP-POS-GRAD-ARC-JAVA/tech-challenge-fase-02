package br.com.fiap.challenge.restautant.infra.web.controller;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import br.com.fiap.challenge.restautant.core.usecase.food.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final CreatedFood createdFood;
    private final ListAllFood listAllFood;
    private final ListFoodById listFoodById;
    private final ListAllFoodByMenu listAllFoodByMenu;
    private final UpdateFood updateFood;
    private final DeleteFood deleteFood;

    public FoodController(CreatedFood createdFood, ListAllFood listAllFood, ListFoodById listFoodById,
                          ListAllFoodByMenu listAllFoodByMenu, UpdateFood updateFood, DeleteFood deleteFood) {
        this.createdFood = createdFood;
        this.listAllFood = listAllFood;
        this.listFoodById = listFoodById;
        this.listAllFoodByMenu = listAllFoodByMenu;
        this.updateFood = updateFood;
        this.deleteFood = deleteFood;
    }

    @PostMapping
    public ResponseEntity<FoodDto> create(@RequestBody FoodInput input) {
        FoodDto result = createdFood.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<FoodDto>> listAll() {
        List<FoodDto> result = listAllFood.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDto> getById(@PathVariable UUID id) {
        FoodDto result = listFoodById.execute(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<FoodDto>> getByMenu(@PathVariable UUID menuId) {
        List<FoodDto> result = listAllFoodByMenu.execute(menuId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodDto> update(@PathVariable UUID id, @RequestBody FoodInput input) {
        FoodInput updatedInput = new FoodInput(id, input.name(), input.description(), input.foodTypeId(),
                input.price(), input.imageUrl(), input.menuId());
        FoodDto result = updateFood.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteFood.execute(id);
        return ResponseEntity.noContent().build();
    }
}