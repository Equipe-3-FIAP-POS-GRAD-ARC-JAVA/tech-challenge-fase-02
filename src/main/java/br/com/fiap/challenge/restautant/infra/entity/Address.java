package br.com.fiap.challenge.restautant.infra.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Address {

    private String street;
    private String number;
    private String city;
    private String neighborhood;
    private String country;

    @Embedded
    private State state;

    @Embedded
    private ZipCode zipCode;

    protected Address() {
    }

    public Address(String street, String number, String city, String neighborhood, String country, State state, ZipCode zipCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.neighborhood = neighborhood;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }
}