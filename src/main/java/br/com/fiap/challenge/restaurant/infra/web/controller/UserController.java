package br.com.fiap.challenge.restaurant.infra.web.controller;

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

import br.com.fiap.challenge.restaurant.core.dto.UserDto;
import br.com.fiap.challenge.restaurant.core.dto.UserInput;
import br.com.fiap.challenge.restaurant.core.usecase.user.CreateUser;
import br.com.fiap.challenge.restaurant.core.usecase.user.DeleteUser;
import br.com.fiap.challenge.restaurant.core.usecase.user.ListAllUser;
import br.com.fiap.challenge.restaurant.core.usecase.user.ListUserById;
import br.com.fiap.challenge.restaurant.core.usecase.user.UpdateUser;
import br.com.fiap.challenge.restaurant.infra.web.api.UserAPI;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserAPI {

    private final CreateUser createUser;
    private final ListAllUser listAllUser;
    private final ListUserById listUserById;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;

    public UserController(CreateUser createUser, ListAllUser listAllUser, ListUserById listUserById,
                          UpdateUser updateUser, DeleteUser deleteUser) {
        this.createUser = createUser;
        this.listAllUser = listAllUser;
        this.listUserById = listUserById;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserInput input) {
        UserDto result = createUser.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listAll() {
        List<UserDto> result = listAllUser.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id) {
        UserDto result = listUserById.execute(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable UUID id, @RequestBody UserInput input) {
        UserInput updatedInput = new UserInput(
                id,
                input.name(),
                input.email(),
                input.login(),
                input.password(),
                input.active(),
                input.role()
        );
        UserDto result = updateUser.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUser.execute(id);
        return ResponseEntity.noContent().build();
    }
}
