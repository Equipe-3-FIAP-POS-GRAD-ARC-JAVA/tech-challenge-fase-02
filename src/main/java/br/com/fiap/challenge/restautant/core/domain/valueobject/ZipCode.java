package br.com.fiap.challenge.restautant.core.domain.valueobject;

public record ZipCode(String code) {
    private static final String FORMAT_REGEX = "\\d{5}-\\d{3}";

    public boolean isValid() {
        return code.matches(FORMAT_REGEX);
    }

    public static ZipCode of(String code) {
        return new ZipCode(code);
    }

    public String getFormattedCode() {
        return code.substring(0, 5) +
                "-" +
                code.substring(5);
    }

    @Override
    public String toString() {
        return getFormattedCode();
    }
}