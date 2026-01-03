package br.com.fiap.challenge.restautant.infra.web.controller;

import br.com.fiap.challenge.restautant.core.dto.UserTypeDto;
import br.com.fiap.challenge.restautant.core.dto.UserTypeInput;
import br.com.fiap.challenge.restautant.core.usecase.userType.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-types")
public class UserTypeController {

    private final CreateUserType createUserType;
    private final ListAllUserType listAllUserType;
    private final ListUserTypeById listUserTypeById;
    private final UpdateUserType updateUserType;
    private final DeleteUserType deleteUserType;

    public UserTypeController(CreateUserType createUserType, ListAllUserType listAllUserType,
                              ListUserTypeById listUserTypeById, UpdateUserType updateUserType,
                              DeleteUserType deleteUserType) {
        this.createUserType = createUserType;
        this.listAllUserType = listAllUserType;
        this.listUserTypeById = listUserTypeById;
        this.updateUserType = updateUserType;
        this.deleteUserType = deleteUserType;
    }

    @PostMapping
    public ResponseEntity<UserTypeDto> create(@RequestBody UserTypeInput input) {
        UserTypeDto result = createUserType.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<UserTypeDto>> listAll() {
        List<UserTypeDto> result = listAllUserType.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTypeDto> getById(@PathVariable UUID id) {
        UserTypeDto result = listUserTypeById.execute(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTypeDto> update(@PathVariable UUID id, @RequestBody UserTypeInput input) {
        UserTypeInput updatedInput = new UserTypeInput(id, input.name());
        UserTypeDto result = updateUserType.execute(updatedInput);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUserType.execute(id);
        return ResponseEntity.noContent().build();
    }
}