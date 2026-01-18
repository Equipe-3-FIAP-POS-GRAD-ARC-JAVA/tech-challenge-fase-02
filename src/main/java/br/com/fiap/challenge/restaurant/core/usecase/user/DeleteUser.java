package br.com.fiap.challenge.restaurant.core.usecase.user;

import java.util.UUID;

public interface DeleteUser {

    void execute(UUID userId);
}
