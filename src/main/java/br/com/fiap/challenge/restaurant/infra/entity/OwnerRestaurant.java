package br.com.fiap.challenge.restaurant.infra.entity;

import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "owner_restaurant",
        uniqueConstraints = @UniqueConstraint(name = "uk_owner_restaurant", columnNames = {"id_client", "id_restaurant"}))
public class OwnerRestaurant {

    @Id
    @UuidGenerator
    @Column(name = "id_relation", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurant", nullable = false)
    private Restaurant restaurant;

    protected OwnerRestaurant() {}

    public OwnerRestaurant(User owner, Restaurant restaurant) {
        this.owner = owner;
        this.restaurant = restaurant;
    }

    public UUID getId() { return id; }
    public User getOwner() { return owner; }
    public Restaurant getRestaurant() { return restaurant; }

    public void setOwner(User owner) { this.owner = owner; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
}
