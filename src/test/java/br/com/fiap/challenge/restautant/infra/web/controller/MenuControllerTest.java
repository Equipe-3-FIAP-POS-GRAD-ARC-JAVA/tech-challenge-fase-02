package br.com.fiap.challenge.restautant.infra.web.controller;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.challenge.restautant.core.dto.MenuDto;
import br.com.fiap.challenge.restautant.core.dto.MenuInput;
import br.com.fiap.challenge.restautant.core.usecase.menu.CreateMenu;
import br.com.fiap.challenge.restautant.core.usecase.menu.DeleteMenu;
import br.com.fiap.challenge.restautant.core.usecase.menu.ListAllMenu;
import br.com.fiap.challenge.restautant.core.usecase.menu.ListAllMenuByRestaurant;
import br.com.fiap.challenge.restautant.core.usecase.menu.ListMenuById;
import br.com.fiap.challenge.restautant.core.usecase.menu.UpdateMenu;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    @InjectMocks
    private MenuController menuController;

    @Mock
    private CreateMenu createMenu;

    @Mock
    private ListAllMenu listAllMenu;

    @Mock
    private ListMenuById listMenuById;

    @Mock
    private ListAllMenuByRestaurant listAllMenuByRestaurant;

    @Mock
    private UpdateMenu updateMenu;

    @Mock
    private DeleteMenu deleteMenu;

    @Test
    @DisplayName("Should create menu successfully")
    void shouldCreateMenu() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuInput input = new MenuInput(null, restaurantId);
        MenuDto expected = new MenuDto(menuId, restaurantId, null);

        when(createMenu.execute(any(MenuInput.class))).thenReturn(expected);

        ResponseEntity<MenuDto> response = menuController.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().menuId()).isEqualTo(menuId);
        assertThat(response.getBody().restaurantId()).isEqualTo(restaurantId);
        verify(createMenu).execute(any(MenuInput.class));
    }

    @Test
    @DisplayName("Should list all menus successfully")
    void shouldListAllMenus() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuDto dto = new MenuDto(menuId, restaurantId, null);

        when(listAllMenu.execute()).thenReturn(List.of(dto));

        ResponseEntity<List<MenuDto>> response = menuController.listAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(1);
        assertThat(response.getBody().get(0).menuId()).isEqualTo(menuId);
        assertThat(response.getBody().get(0).restaurantId()).isEqualTo(restaurantId);
        verify(listAllMenu).execute();
    }

    @Test
    @DisplayName("Should get menu by id successfully")
    void shouldGetMenuById() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuDto dto = new MenuDto(menuId, restaurantId, null);

        when(listMenuById.execute(menuId)).thenReturn(dto);

        ResponseEntity<MenuDto> response = menuController.getById(menuId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().menuId()).isEqualTo(menuId);
        assertThat(response.getBody().restaurantId()).isEqualTo(restaurantId);
        verify(listMenuById).execute(menuId);
    }

    @Test
    @DisplayName("Should return not found when menu does not exist")
    void shouldReturnNotFoundWhenMenuDoesNotExist() {
        UUID menuId = UUID.randomUUID();

        when(listMenuById.execute(menuId)).thenReturn(null);

        ResponseEntity<MenuDto> response = menuController.getById(menuId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(listMenuById).execute(menuId);
    }

    @Test
    @DisplayName("Should get menus by restaurant successfully")
    void shouldGetMenusByRestaurant() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuDto dto = new MenuDto(menuId, restaurantId, null);

        when(listAllMenuByRestaurant.execute(restaurantId)).thenReturn(List.of(dto));

        ResponseEntity<List<MenuDto>> response = menuController.getByRestaurant(restaurantId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(1);
        assertThat(response.getBody().get(0).menuId()).isEqualTo(menuId);
        assertThat(response.getBody().get(0).restaurantId()).isEqualTo(restaurantId);
        verify(listAllMenuByRestaurant).execute(restaurantId);
    }

    @Test
    @DisplayName("Should update menu successfully")
    void shouldUpdateMenu() {
        UUID menuId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        MenuInput input = new MenuInput(null, restaurantId);
        MenuDto expected = new MenuDto(menuId, restaurantId, null);

        when(updateMenu.execute(any(MenuInput.class))).thenReturn(expected);

        ResponseEntity<MenuDto> response = menuController.update(menuId, input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().menuId()).isEqualTo(menuId);
        assertThat(response.getBody().restaurantId()).isEqualTo(restaurantId);
        verify(updateMenu).execute(any(MenuInput.class));
    }

    @Test
    @DisplayName("Should delete menu successfully")
    void shouldDeleteMenu() {
        UUID menuId = UUID.randomUUID();

        doNothing().when(deleteMenu).execute(menuId);

        ResponseEntity<Void> response = menuController.delete(menuId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
        verify(deleteMenu).execute(menuId);
    }
}