package br.com.fiap.challenge.restaurant.core.usecase.menu;

import br.com.fiap.challenge.restaurant.core.dto.MenuDto;

import java.util.List;

public interface ListAllMenu {
    List<MenuDto> execute();
}