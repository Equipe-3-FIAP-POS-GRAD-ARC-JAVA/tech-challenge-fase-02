package br.com.fiap.challenge.restaurant.infra.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ZipCode {

    private String code;

    protected ZipCode() {
    }

    public ZipCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isValid() {
        return code != null && code.matches("\\d{5}-\\d{3}");
    }

    public static ZipCode of(String code) {
        return new ZipCode(code);
    }

    public String getFormattedCode() {
        if (code == null || code.length() != 8) return code;
        return code.substring(0, 5) + "-" + code.substring(5);
    }

    @Override
    public String toString() {
        return getFormattedCode();
    }
}