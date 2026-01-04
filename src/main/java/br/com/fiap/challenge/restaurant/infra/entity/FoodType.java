package br.com.fiap.challenge.restaurant.infra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_types")
public class FoodType extends BaseEntity {

    private String typeFood;

    protected FoodType() {
    }

    public FoodType(String typeFood) {
        this.typeFood = typeFood;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }
}