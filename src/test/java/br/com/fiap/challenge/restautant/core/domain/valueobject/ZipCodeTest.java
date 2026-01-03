package br.com.fiap.challenge.restautant.core.domain.valueobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ZipCodeTest {

    @Test
    @DisplayName("Should create ZipCode with valid format")
    void shouldCreateZipCodeWithValidFormat() {
        // Given
        String validCode = "12345-678";
        
        // When
        ZipCode zipCode = new ZipCode(validCode);
        
        // Then
        assertThat(zipCode.code()).isEqualTo(validCode);
    }

    @Test
    @DisplayName("Should validate ZipCode with correct format")
    void shouldValidateZipCodeWithCorrectFormat() {
        // Given
        ZipCode zipCode = new ZipCode("12345-678");
        
        // When
        boolean isValid = zipCode.isValid();
        
        // Then
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("Should invalidate ZipCode with incorrect format")
    void shouldInvalidateZipCodeWithIncorrectFormat() {
        // Given
        ZipCode zipCode = new ZipCode("1234-567");
        
        // When
        boolean isValid = zipCode.isValid();
        
        // Then
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Should create ZipCode using factory method")
    void shouldCreateZipCodeUsingFactoryMethod() {
        // Given
        String code = "12345-678";
        
        // When
        ZipCode zipCode = ZipCode.of(code);
        
        // Then
        assertThat(zipCode).isNotNull();
        assertThat(zipCode.code()).isEqualTo(code);
    }

    @Test
    @DisplayName("Should format ZipCode correctly")
    void shouldFormatZipCodeCorrectly() {
        // Given
        ZipCode zipCode = new ZipCode("12345678");
        
        // When
        String formatted = zipCode.getFormattedCode();
        
        // Then
        assertThat(formatted).isEqualTo("12345-678");
    }

    @Test
    @DisplayName("Should return formatted code in toString")
    void shouldReturnFormattedCodeInToString() {
        // Given
        ZipCode zipCode = new ZipCode("12345678");
        
        // When
        String result = zipCode.toString();
        
        // Then
        assertThat(result).isEqualTo("12345-678");
    }

    @Test
    @DisplayName("Should validate ZipCode with letters as invalid")
    void shouldValidateZipCodeWithLettersAsInvalid() {
        // Given
        ZipCode zipCode = new ZipCode("ABCDE-FGH");
        
        // When
        boolean isValid = zipCode.isValid();
        
        // Then
        assertThat(isValid).isFalse();
    }
}
