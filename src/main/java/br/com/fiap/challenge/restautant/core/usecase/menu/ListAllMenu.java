package br.com.fiap.challenge.restautant.core.usecase.menu;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;

import java.util.List;

public interface ListAllMenu {
    List<MenuDto> execute();
}