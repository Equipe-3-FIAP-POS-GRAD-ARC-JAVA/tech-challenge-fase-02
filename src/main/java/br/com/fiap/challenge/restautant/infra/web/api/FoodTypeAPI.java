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

import br.com.fiap.challenge.restautant.core.dto.FoodTypeDto;
import br.com.fiap.challenge.restautant.core.dto.FoodTypeInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Food Type", description = "API for managing food types in the restaurant system")
public interface FoodTypeAPI {

    @Operation(summary = "Create a new food type", description = "Creates a new food type in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Food type created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<FoodTypeDto> create(@RequestBody FoodTypeInput input);

    @Operation(summary = "List all food types", description = "Retrieves a list of all food types")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of food types retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<FoodTypeDto>> listAll();

    @Operation(summary = "Get food type by ID", description = "Retrieves a specific food type by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Food type retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Food type not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    ResponseEntity<FoodTypeDto> getById(@Parameter(description = "Food type ID") @PathVariable UUID id);

    @Operation(summary = "Update food type", description = "Updates an existing food type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Food type updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Food type not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    ResponseEntity<FoodTypeDto> update(@Parameter(description = "Food type ID") @PathVariable UUID id, @RequestBody FoodTypeInput input);

    @Operation(summary = "Delete food type", description = "Deletes a food type from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Food type deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Food type not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "Food type ID") @PathVariable UUID id);
}