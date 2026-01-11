package br.com.fiap.challenge.restaurant.infra.web.controller;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.LinkOwnerRestaurant;
import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.ListOwnerRestaurantByOwner;
import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.ListOwnerRestaurantByRestaurant;
import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.UnlinkOwnerRestaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantInput;

@RestController
@RequestMapping("/api")
public class OwnerRestaurantController {

    private final LinkOwnerRestaurant linkOwnerRestaurant;
    private final UnlinkOwnerRestaurant unlinkOwnerRestaurant;
    private final ListOwnerRestaurantByOwner listByOwner;
    private final ListOwnerRestaurantByRestaurant listByRestaurant;

    public OwnerRestaurantController(
            LinkOwnerRestaurant linkOwnerRestaurant,
            UnlinkOwnerRestaurant unlinkOwnerRestaurant,
            ListOwnerRestaurantByOwner listByOwner,
            ListOwnerRestaurantByRestaurant listByRestaurant
    ) {
        this.linkOwnerRestaurant = linkOwnerRestaurant;
        this.unlinkOwnerRestaurant = unlinkOwnerRestaurant;
        this.listByOwner = listByOwner;
        this.listByRestaurant = listByRestaurant;
    }

    @PostMapping("/owners/restaurants")
    public ResponseEntity<OwnerRestaurantDto> link(@RequestBody OwnerRestaurantInput input) {
        OwnerRestaurantDto result = linkOwnerRestaurant.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/owners/{ownerId}/restaurants/{restaurantId}")
    public ResponseEntity<Void> unlink(@PathVariable UUID ownerId, @PathVariable UUID restaurantId) {
        unlinkOwnerRestaurant.execute(ownerId, restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owners/{ownerId}/restaurants")
    public ResponseEntity<List<OwnerRestaurantDto>> listRestaurantsByOwner(@PathVariable UUID ownerId) {
        List<OwnerRestaurantDto> result = listByOwner.execute(ownerId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/restaurants/{restaurantId}/owners")
    public ResponseEntity<List<OwnerRestaurantDto>> listOwnersByRestaurant(@PathVariable UUID restaurantId) {
        List<OwnerRestaurantDto> result = listByRestaurant.execute(restaurantId);
        return ResponseEntity.ok(result);
    }
}
