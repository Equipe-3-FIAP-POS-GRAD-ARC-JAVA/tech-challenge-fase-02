package br.com.fiap.challenge.restautant.infra.exceptions;

import java.net.URI;

import org.springframework.http.ProblemDetail;

public class ProblemDetailBuilder {

    private final ProblemDetail problemDetail;

    private ProblemDetailBuilder() {
        this.problemDetail = ProblemDetail.forStatus(500); // Default status, can be overridden
    }

    public static ProblemDetailBuilder builder() {
        return new ProblemDetailBuilder();
    }

    public ProblemDetailBuilder type(URI type) {
        problemDetail.setType(type);
        return this;
    }

    public ProblemDetailBuilder title(String title) {
        problemDetail.setTitle(title);
        return this;
    }

    public ProblemDetailBuilder status(int status) {
        problemDetail.setStatus(status);
        return this;
    }

    public ProblemDetailBuilder detail(String detail) {
        problemDetail.setDetail(detail);
        return this;
    }

    public ProblemDetailBuilder instance(URI instance) {
        problemDetail.setInstance(instance);
        return this;
    }

    public ProblemDetailBuilder property(String key, Object value) {
        problemDetail.setProperty(key, value);
        return this;
    }

    public ProblemDetail build() {
        return problemDetail;
    }
}