package br.com.fiap.challenge.restaurant.infra.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.UUID;

import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.LinkOwnerRestaurant;
import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.ListOwnerRestaurantByOwner;
import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.ListOwnerRestaurantByRestaurant;
import br.com.fiap.challenge.restaurant.core.usecase.OwnerRestaurant.UnlinkOwnerRestaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantInput;

@WebMvcTest(OwnerRestaurantController.class)
class OwnerRestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private LinkOwnerRestaurant linkOwnerRestaurant;

    @MockitoBean
    private UnlinkOwnerRestaurant unlinkOwnerRestaurant;

    @MockitoBean
    private ListOwnerRestaurantByOwner listByOwner;

    @MockitoBean
    private ListOwnerRestaurantByRestaurant listByRestaurant;

    @Test
    void shouldLinkOwnerToRestaurant() throws Exception {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        UUID relationId = UUID.randomUUID();

        OwnerRestaurantInput input = new OwnerRestaurantInput(ownerId, restaurantId);
        OwnerRestaurantDto output = new OwnerRestaurantDto(relationId, ownerId, restaurantId);

        when(linkOwnerRestaurant.execute(any(OwnerRestaurantInput.class))).thenReturn(output);

        mockMvc.perform(post("/api/owners/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(relationId.toString()))
                .andExpect(jsonPath("$.ownerId").value(ownerId.toString()))
                .andExpect(jsonPath("$.restaurantId").value(restaurantId.toString()));

        verify(linkOwnerRestaurant).execute(any(OwnerRestaurantInput.class));
        verifyNoMoreInteractions(linkOwnerRestaurant, unlinkOwnerRestaurant, listByOwner, listByRestaurant);
    }

    @Test
    void shouldListRestaurantsByOwner() throws Exception {
        UUID ownerId = UUID.randomUUID();

        List<OwnerRestaurantDto> list = List.of(
                new OwnerRestaurantDto(UUID.randomUUID(), ownerId, UUID.randomUUID()),
                new OwnerRestaurantDto(UUID.randomUUID(), ownerId, UUID.randomUUID())
        );

        when(listByOwner.execute(ownerId)).thenReturn(list);

        mockMvc.perform(get("/api/owners/{ownerId}/restaurants", ownerId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].ownerId").value(ownerId.toString()));

        verify(listByOwner).execute(ownerId);
        verifyNoMoreInteractions(linkOwnerRestaurant, unlinkOwnerRestaurant, listByOwner, listByRestaurant);
    }

    @Test
    void shouldListOwnersByRestaurant() throws Exception {
        UUID restaurantId = UUID.randomUUID();

        List<OwnerRestaurantDto> list = List.of(
                new OwnerRestaurantDto(UUID.randomUUID(), UUID.randomUUID(), restaurantId),
                new OwnerRestaurantDto(UUID.randomUUID(), UUID.randomUUID(), restaurantId)
        );

        when(listByRestaurant.execute(restaurantId)).thenReturn(list);

        mockMvc.perform(get("/api/restaurants/{restaurantId}/owners", restaurantId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].restaurantId").value(restaurantId.toString()));

        verify(listByRestaurant).execute(restaurantId);
        verifyNoMoreInteractions(linkOwnerRestaurant, unlinkOwnerRestaurant, listByOwner, listByRestaurant);
    }

    @Test
    void shouldUnlinkOwnerFromRestaurant() throws Exception {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        mockMvc.perform(delete("/api/owners/{ownerId}/restaurants/{restaurantId}", ownerId, restaurantId))
                .andExpect(status().isNoContent());

        verify(unlinkOwnerRestaurant).execute(ownerId, restaurantId);
        verifyNoMoreInteractions(linkOwnerRestaurant, unlinkOwnerRestaurant, listByOwner, listByRestaurant);
    }
}
