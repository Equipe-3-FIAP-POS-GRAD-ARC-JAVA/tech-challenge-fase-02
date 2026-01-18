package br.com.fiap.challenge.restaurant.core.domain.entity;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;

public class User {
    private String name;
    private String email;
    private String login;
    private String password;
    private boolean active;
    private Role role;
}
