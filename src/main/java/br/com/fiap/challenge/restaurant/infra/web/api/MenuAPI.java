package br.com.fiap.challenge.restaurant.infra.web.api;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;
import br.com.fiap.challenge.restaurant.core.dto.MenuInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Menu", description = "API for managing menus in the restaurant system")
public interface MenuAPI {

    @Operation(summary = "Create a new menu", description = "Creates a new menu in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Menu created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<MenuDto> create(@RequestBody MenuInput input);

    @Operation(summary = "List all menus", description = "Retrieves a list of all menus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of menus retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<MenuDto>> listAll();

    @Operation(summary = "Get menu by ID", description = "Retrieves a specific menu by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Menu not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    ResponseEntity<MenuDto> getById(@Parameter(description = "Menu ID") @PathVariable UUID id);

    @Operation(summary = "Get menus by restaurant", description = "Retrieves all menus associated with a specific restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of menus retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/restaurant/{restaurantId}")
    ResponseEntity<List<MenuDto>> getByRestaurant(@Parameter(description = "Restaurant ID") @PathVariable UUID restaurantId);

    @Operation(summary = "Update menu", description = "Updates an existing menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Menu not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    ResponseEntity<MenuDto> update(@Parameter(description = "Menu ID") @PathVariable UUID id, @RequestBody MenuInput input);

    @Operation(summary = "Delete menu", description = "Deletes a menu from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Menu deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Menu not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "Menu ID") @PathVariable UUID id);
}