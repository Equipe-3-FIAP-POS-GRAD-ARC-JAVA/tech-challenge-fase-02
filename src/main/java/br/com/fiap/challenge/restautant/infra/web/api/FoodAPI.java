package br.com.fiap.challenge.restautant.infra.web.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.challenge.restautant.core.dto.FoodDto;
import br.com.fiap.challenge.restautant.core.dto.FoodInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Food", description = "API for managing food items in the restaurant system")
public interface FoodAPI {

    @Operation(summary = "Create a new food item", description = "Creates a new food item in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Food item created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<FoodDto> create(@RequestBody FoodInput input);

    @Operation(summary = "List all food items", description = "Retrieves a list of all food items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of food items retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<FoodDto>> listAll();

    @Operation(summary = "Get food item by ID", description = "Retrieves a specific food item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Food item retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Food item not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    ResponseEntity<FoodDto> getById(@Parameter(description = "Food item ID") @PathVariable UUID id);

    @Operation(summary = "Get food items by menu", description = "Retrieves all food items associated with a specific menu")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of food items retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Menu not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/menu/{menuId}")
    ResponseEntity<List<FoodDto>> getByMenu(@Parameter(description = "Menu ID") @PathVariable UUID menuId);

    @Operation(summary = "Update food item", description = "Updates an existing food item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Food item updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Food item not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    ResponseEntity<FoodDto> update(@Parameter(description = "Food item ID") @PathVariable UUID id, @RequestBody FoodInput input);

    @Operation(summary = "Delete food item", description = "Deletes a food item from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Food item deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Food item not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "Food item ID") @PathVariable UUID id);
}