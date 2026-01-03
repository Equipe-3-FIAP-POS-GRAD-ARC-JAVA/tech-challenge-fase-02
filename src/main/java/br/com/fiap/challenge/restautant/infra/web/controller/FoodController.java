package br.com.fiap.challenge.restautant.infra.web.controller;

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

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import br.com.fiap.challenge.restautant.core.usecase.food.CreatedFood;
import br.com.fiap.challenge.restautant.core.usecase.food.DeleteFood;
import br.com.fiap.challenge.restautant.core.usecase.food.ListAllFood;
import br.com.fiap.challenge.restautant.core.usecase.food.ListAllFoodByMenu;
import br.com.fiap.challenge.restautant.core.usecase.food.ListFoodById;
import br.com.fiap.challenge.restautant.core.usecase.food.UpdateFood;
import br.com.fiap.challenge.restautant.infra.web.api.FoodAPI;

@RestController
@RequestMapping("/api/foods")
public class FoodController implements FoodAPI {

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
        FoodInput updatedInput = new FoodInput(id, input.menuId(), input.name(), input.description(), input.foodTypeId(),
                input.price(), input.imageURL());
        FoodDto result = updateFood.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteFood.execute(id);
        return ResponseEntity.noContent().build();
    }
}