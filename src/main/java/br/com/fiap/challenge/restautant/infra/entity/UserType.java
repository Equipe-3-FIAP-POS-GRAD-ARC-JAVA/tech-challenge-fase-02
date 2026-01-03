package br.com.fiap.challenge.restautant.infra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_types")
public class UserType extends BaseEntity {

    private String name;

    protected UserType() {
    }

    public UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}