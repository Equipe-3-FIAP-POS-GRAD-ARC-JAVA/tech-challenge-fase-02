package br.com.fiap.challenge.restautant.infra.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ZipCodeEntityTest {

    @Test
    @DisplayName("Should create ZipCode with code")
    void shouldCreateZipCodeWithCode() {
        // Given
        String code = "12345-678";
        
        // When
        ZipCode zipCode = new ZipCode(code);
        
        // Then
        assertThat(zipCode.getCode()).isEqualTo(code);
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
    @DisplayName("Should invalidate null ZipCode")
    void shouldInvalidateNullZipCode() {
        // Given
        ZipCode zipCode = new ZipCode(null);
        
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
        assertThat(zipCode.getCode()).isEqualTo(code);
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
    @DisplayName("Should return original code if length is not 8")
    void shouldReturnOriginalCodeIfLengthIsNot8() {
        // Given
        ZipCode zipCode = new ZipCode("123");
        
        // When
        String formatted = zipCode.getFormattedCode();
        
        // Then
        assertThat(formatted).isEqualTo("123");
    }

    @Test
    @DisplayName("Should return null in formatted code when code is null")
    void shouldReturnNullInFormattedCodeWhenCodeIsNull() {
        // Given
        ZipCode zipCode = new ZipCode(null);
        
        // When
        String formatted = zipCode.getFormattedCode();
        
        // Then
        assertThat(formatted).isNull();
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
    @DisplayName("Should update code")
    void shouldUpdateCode() {
        // Given
        ZipCode zipCode = new ZipCode("12345-678");
        
        // When
        zipCode.setCode("98765-432");
        
        // Then
        assertThat(zipCode.getCode()).isEqualTo("98765-432");
    }
}
