package br.com.fiap.challenge.restaurant.infra.web.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursDto;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursInput;
import br.com.fiap.challenge.restaurant.core.dto.OpeningHoursUpdateInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Opening Hours", description = "API for managing restaurant opening hours")
public interface OpeningHoursAPI {

    @Operation(summary = "Create opening hours", description = "Creates opening hours for a restaurant and weekday")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Opening hours created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<OpeningHoursDto> create(@RequestBody OpeningHoursInput input);

    @Operation(summary = "List opening hours by restaurant", description = "Retrieves opening hours for a specific restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opening hours retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/restaurant/{restaurantId}")
    ResponseEntity<List<OpeningHoursDto>> listByRestaurant(
            @Parameter(description = "Restaurant ID") @PathVariable UUID restaurantId
    );

    @Operation(summary = "Update opening hours", description = "Updates an existing opening hours entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opening hours updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Opening hours not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    ResponseEntity<OpeningHoursDto> update(
            @Parameter(description = "Opening hours ID") @PathVariable UUID id,
            @RequestBody OpeningHoursUpdateInput input
    );

    @Operation(summary = "Delete opening hours", description = "Deletes an opening hours entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Opening hours deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Opening hours not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "Opening hours ID") @PathVariable UUID id);
}
