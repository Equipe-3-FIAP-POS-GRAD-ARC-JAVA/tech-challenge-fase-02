package br.com.fiap.challenge.restaurant.infra.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.challenge.restaurant.core.domain.enums.Role;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantDto;
import br.com.fiap.challenge.restaurant.core.dto.OwnerRestaurantInput;
import br.com.fiap.challenge.restaurant.infra.entity.OwnerRestaurant;
import br.com.fiap.challenge.restaurant.infra.entity.Restaurant;
import br.com.fiap.challenge.restaurant.infra.entity.User;
import br.com.fiap.challenge.restaurant.infra.repository.OwnerRestaurantRepository;
import br.com.fiap.challenge.restaurant.infra.repository.RestaurantRepository;
import br.com.fiap.challenge.restaurant.infra.repository.UserRepository;

class OwnerRestaurantGatewayAdapterTest {

    @Mock
    private OwnerRestaurantRepository ownerRestaurantRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private OwnerRestaurantGatewayAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLinkOwnerToRestaurant() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        UUID relationId = UUID.randomUUID();

        OwnerRestaurantInput input = new OwnerRestaurantInput(ownerId, restaurantId);

        User owner = mock(User.class);
        when(owner.getId()).thenReturn(ownerId);
        when(owner.getRole()).thenReturn(Role.OWNER_RESTAURANT);

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurant.getId()).thenReturn(restaurantId);

        OwnerRestaurant saved = mock(OwnerRestaurant.class);
        when(saved.getId()).thenReturn(relationId);
        when(saved.getOwner()).thenReturn(owner);
        when(saved.getRestaurant()).thenReturn(restaurant);

        when(ownerRestaurantRepository.existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId)).thenReturn(false);
        when(userRepository.findById(ownerId)).thenReturn(Optional.of(owner));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(ownerRestaurantRepository.save(any(OwnerRestaurant.class))).thenReturn(saved);

        OwnerRestaurantDto result = adapter.link(input);

        assertNotNull(result);
        assertEquals(relationId, result.id());
        assertEquals(ownerId, result.ownerId());
        assertEquals(restaurantId, result.restaurantId());

        verify(ownerRestaurantRepository).existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
        verify(userRepository).findById(ownerId);
        verify(restaurantRepository).findById(restaurantId);
        verify(ownerRestaurantRepository).save(any(OwnerRestaurant.class));
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldThrowWhenRelationAlreadyExists() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        OwnerRestaurantInput input = new OwnerRestaurantInput(ownerId, restaurantId);

        when(ownerRestaurantRepository.existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId)).thenReturn(true);

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> adapter.link(input));
        assertNotNull(ex);

        verify(ownerRestaurantRepository).existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        OwnerRestaurantInput input = new OwnerRestaurantInput(ownerId, restaurantId);

        when(ownerRestaurantRepository.existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId)).thenReturn(false);
        when(userRepository.findById(ownerId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> adapter.link(input));
        assertNotNull(ex);

        verify(ownerRestaurantRepository).existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
        verify(userRepository).findById(ownerId);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldThrowWhenUserIsNotOwnerRestaurant() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        OwnerRestaurantInput input = new OwnerRestaurantInput(ownerId, restaurantId);

        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.CUSTOMER);

        when(ownerRestaurantRepository.existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId)).thenReturn(false);
        when(userRepository.findById(ownerId)).thenReturn(Optional.of(user));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> adapter.link(input));
        assertNotNull(ex);

        verify(ownerRestaurantRepository).existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
        verify(userRepository).findById(ownerId);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldThrowWhenRestaurantNotFound() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        OwnerRestaurantInput input = new OwnerRestaurantInput(ownerId, restaurantId);

        User owner = mock(User.class);
        when(owner.getRole()).thenReturn(Role.OWNER_RESTAURANT);

        when(ownerRestaurantRepository.existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId)).thenReturn(false);
        when(userRepository.findById(ownerId)).thenReturn(Optional.of(owner));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> adapter.link(input));
        assertNotNull(ex);

        verify(ownerRestaurantRepository).existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
        verify(userRepository).findById(ownerId);
        verify(restaurantRepository).findById(restaurantId);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldUnlinkOwnerFromRestaurant() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        OwnerRestaurant rel = mock(OwnerRestaurant.class);

        when(ownerRestaurantRepository.findByOwner_IdAndRestaurant_Id(ownerId, restaurantId))
                .thenReturn(Optional.of(rel));

        adapter.unlink(ownerId, restaurantId);

        verify(ownerRestaurantRepository).findByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
        verify(ownerRestaurantRepository).delete(rel);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldThrowWhenUnlinkRelationNotFound() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        when(ownerRestaurantRepository.findByOwner_IdAndRestaurant_Id(ownerId, restaurantId))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> adapter.unlink(ownerId, restaurantId));
        assertNotNull(ex);

        verify(ownerRestaurantRepository).findByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldListByOwner() {
        UUID ownerId = UUID.randomUUID();

        OwnerRestaurant r1 = mockRel(UUID.randomUUID(), ownerId, UUID.randomUUID());
        OwnerRestaurant r2 = mockRel(UUID.randomUUID(), ownerId, UUID.randomUUID());

        when(ownerRestaurantRepository.findAllByOwner_Id(ownerId)).thenReturn(List.of(r1, r2));

        List<OwnerRestaurantDto> result = adapter.listByOwner(ownerId);

        assertEquals(2, result.size());
        assertEquals(ownerId, result.get(0).ownerId());

        verify(ownerRestaurantRepository).findAllByOwner_Id(ownerId);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldListByRestaurant() {
        UUID restaurantId = UUID.randomUUID();

        OwnerRestaurant r1 = mockRel(UUID.randomUUID(), UUID.randomUUID(), restaurantId);
        OwnerRestaurant r2 = mockRel(UUID.randomUUID(), UUID.randomUUID(), restaurantId);

        when(ownerRestaurantRepository.findAllByRestaurant_Id(restaurantId)).thenReturn(List.of(r1, r2));

        List<OwnerRestaurantDto> result = adapter.listByRestaurant(restaurantId);

        assertEquals(2, result.size());
        assertEquals(restaurantId, result.get(0).restaurantId());

        verify(ownerRestaurantRepository).findAllByRestaurant_Id(restaurantId);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    @Test
    void shouldReturnExists() {
        UUID ownerId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();

        when(ownerRestaurantRepository.existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId)).thenReturn(true);

        boolean result = adapter.exists(ownerId, restaurantId);

        assertTrue(result);
        verify(ownerRestaurantRepository).existsByOwner_IdAndRestaurant_Id(ownerId, restaurantId);
        verifyNoMoreInteractions(ownerRestaurantRepository, userRepository, restaurantRepository);
    }

    private OwnerRestaurant mockRel(UUID relId, UUID ownerId, UUID restaurantId) {
        User owner = mock(User.class);
        when(owner.getId()).thenReturn(ownerId);

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurant.getId()).thenReturn(restaurantId);

        OwnerRestaurant rel = mock(OwnerRestaurant.class);
        when(rel.getId()).thenReturn(relId);
        when(rel.getOwner()).thenReturn(owner);
        when(rel.getRestaurant()).thenReturn(restaurant);

        return rel;
    }
}
