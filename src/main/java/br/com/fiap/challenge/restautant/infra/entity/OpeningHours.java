package br.com.fiap.challenge.restautant.infra.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "menus")
public class OpeningHours extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> foods;

    protected OpeningHours() {
    }

    public OpeningHours(Restaurant restaurant, List<Food> foods) {
        this.restaurant = restaurant;
        this.foods = foods;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}