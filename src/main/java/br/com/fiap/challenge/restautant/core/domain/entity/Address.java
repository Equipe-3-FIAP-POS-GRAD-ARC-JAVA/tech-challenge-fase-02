package br.com.fiap.challenge.restautant.core.domain.entity;

import br.com.fiap.challenge.restautant.core.domain.valueobject.State;
import br.com.fiap.challenge.restautant.core.domain.valueobject.ZipCode;

public class Address extends BaseEntity  {
    private String street;
    private String number; 
    private String city;
    private String neighborhood;
    private String country;
    private State state; 
    private ZipCode zipCode;
}
