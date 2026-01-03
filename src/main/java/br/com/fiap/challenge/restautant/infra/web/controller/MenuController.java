package br.com.fiap.challenge.restautant.infra.web.controller;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.dto.MenuInput;
import br.com.fiap.challenge.restautant.core.usecase.menu.*;
import br.com.fiap.challenge.restautant.infra.web.api.MenuAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menus")
public class MenuController implements MenuAPI {

    private final CreateMenu createMenu;
    private final ListAllMenu listAllMenu;
    private final ListMenuById listMenuById;
    private final ListAllMenuByRestaurant listAllMenuByRestaurant;
    private final UpdateMenu updateMenu;
    private final DeleteMenu deleteMenu;

    public MenuController(CreateMenu createMenu, ListAllMenu listAllMenu, ListMenuById listMenuById,
                          ListAllMenuByRestaurant listAllMenuByRestaurant, UpdateMenu updateMenu, DeleteMenu deleteMenu) {
        this.createMenu = createMenu;
        this.listAllMenu = listAllMenu;
        this.listMenuById = listMenuById;
        this.listAllMenuByRestaurant = listAllMenuByRestaurant;
        this.updateMenu = updateMenu;
        this.deleteMenu = deleteMenu;
    }

    @PostMapping
    public ResponseEntity<MenuDto> create(@RequestBody MenuInput input) {
        MenuDto result = createMenu.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<MenuDto>> listAll() {
        List<MenuDto> result = listAllMenu.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getById(@PathVariable UUID id) {
        MenuDto result = listMenuById.execute(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuDto>> getByRestaurant(@PathVariable UUID restaurantId) {
        List<MenuDto> result = listAllMenuByRestaurant.execute(restaurantId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> update(@PathVariable UUID id, @RequestBody MenuInput input) {
        MenuInput updatedInput = new MenuInput(id, input.restaurantId());
        MenuDto result = updateMenu.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteMenu.execute(id);
        return ResponseEntity.noContent().build();
    }
}