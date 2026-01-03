package br.com.fiap.challenge.restautant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    @DisplayName("Should create Address with all parameters")
    void shouldCreateAddressWithAllParameters() {
        // Given
        State state = new State("SP");
        ZipCode zipCode = new ZipCode("12345-678");
        
        // When
        Address address = new Address(
            "Rua Teste",
            "123",
            "São Paulo",
            "Centro",
            "Brasil",
            state,
            zipCode
        );
        
        // Then
        assertThat(address.getStreet()).isEqualTo("Rua Teste");
        assertThat(address.getNumber()).isEqualTo("123");
        assertThat(address.getCity()).isEqualTo("São Paulo");
        assertThat(address.getNeighborhood()).isEqualTo("Centro");
        assertThat(address.getCountry()).isEqualTo("Brasil");
        assertThat(address.getState()).isEqualTo(state);
        assertThat(address.getZipCode()).isEqualTo(zipCode);
    }

    @Test
    @DisplayName("Should update street")
    void shouldUpdateStreet() {
        // Given
        Address address = new Address("Rua Antiga", "123", "São Paulo", "Centro", "Brasil", 
            new State("SP"), new ZipCode("12345-678"));
        
        // When
        address.setStreet("Rua Nova");
        
        // Then
        assertThat(address.getStreet()).isEqualTo("Rua Nova");
    }

    @Test
    @DisplayName("Should update number")
    void shouldUpdateNumber() {
        // Given
        Address address = new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil", 
            new State("SP"), new ZipCode("12345-678"));
        
        // When
        address.setNumber("456");
        
        // Then
        assertThat(address.getNumber()).isEqualTo("456");
    }

    @Test
    @DisplayName("Should update city")
    void shouldUpdateCity() {
        // Given
        Address address = new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil", 
            new State("SP"), new ZipCode("12345-678"));
        
        // When
        address.setCity("Rio de Janeiro");
        
        // Then
        assertThat(address.getCity()).isEqualTo("Rio de Janeiro");
    }

    @Test
    @DisplayName("Should update neighborhood")
    void shouldUpdateNeighborhood() {
        // Given
        Address address = new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil", 
            new State("SP"), new ZipCode("12345-678"));
        
        // When
        address.setNeighborhood("Copacabana");
        
        // Then
        assertThat(address.getNeighborhood()).isEqualTo("Copacabana");
    }

    @Test
    @DisplayName("Should update country")
    void shouldUpdateCountry() {
        // Given
        Address address = new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil", 
            new State("SP"), new ZipCode("12345-678"));
        
        // When
        address.setCountry("Argentina");
        
        // Then
        assertThat(address.getCountry()).isEqualTo("Argentina");
    }

    @Test
    @DisplayName("Should update state")
    void shouldUpdateState() {
        // Given
        Address address = new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil", 
            new State("SP"), new ZipCode("12345-678"));
        State newState = new State("RJ");
        
        // When
        address.setState(newState);
        
        // Then
        assertThat(address.getState()).isEqualTo(newState);
    }

    @Test
    @DisplayName("Should update zipCode")
    void shouldUpdateZipCode() {
        // Given
        Address address = new Address("Rua Teste", "123", "São Paulo", "Centro", "Brasil", 
            new State("SP"), new ZipCode("12345-678"));
        ZipCode newZipCode = new ZipCode("98765-432");
        
        // When
        address.setZipCode(newZipCode);
        
        // Then
        assertThat(address.getZipCode()).isEqualTo(newZipCode);
    }
}
