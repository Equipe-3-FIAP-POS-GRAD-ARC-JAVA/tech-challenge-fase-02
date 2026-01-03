package br.com.fiap.challenge.restautant.infra.web.controller;

import br.com.fiap.challenge.restautant.core.dto.RestaurantDto;
import br.com.fiap.challenge.restautant.core.dto.RestaurantInput;
import br.com.fiap.challenge.restautant.core.usecase.restaurant.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final CreateRestaurant createRestaurant;
    private final ListAllRestaurant listAllRestaurant;
    private final ListRestaurantById listRestaurantById;
    private final UpdateRestaurant updateRestaurant;
    private final DeleteRestaurant deleteRestaurant;

    public RestaurantController(CreateRestaurant createRestaurant, ListAllRestaurant listAllRestaurant,
                                ListRestaurantById listRestaurantById, UpdateRestaurant updateRestaurant,
                                DeleteRestaurant deleteRestaurant) {
        this.createRestaurant = createRestaurant;
        this.listAllRestaurant = listAllRestaurant;
        this.listRestaurantById = listRestaurantById;
        this.updateRestaurant = updateRestaurant;
        this.deleteRestaurant = deleteRestaurant;
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> create(@RequestBody RestaurantInput input) {
        RestaurantDto result = createRestaurant.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> listAll() {
        List<RestaurantDto> result = listAllRestaurant.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getById(@PathVariable UUID id) {
        RestaurantDto result = listRestaurantById.execute(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> update(@PathVariable UUID id, @RequestBody RestaurantInput input) {
        RestaurantInput updatedInput = new RestaurantInput(id, input.name(), input.address());
        RestaurantDto result = updateRestaurant.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteRestaurant.execute(id);
        return ResponseEntity.noContent().build();
    }
}