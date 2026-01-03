package br.com.fiap.challenge.restautant.infra.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;

class ProblemDetailBuilderTest {

    @Test
    void shouldBuildProblemDetailWithAllFields() {
        URI type = URI.create("http://example.com/error");
        String title = "Validation Error";
        int status = 400;
        String detail = "Invalid input";
        URI instance = URI.create("http://example.com/instance");

        ProblemDetail pd = ProblemDetailBuilder.builder()
            .type(type)
            .title(title)
            .status(status)
            .detail(detail)
            .instance(instance)
            .property("errorCode", "VALIDATION_FAILED")
            .build();

        assertThat(pd.getType()).isEqualTo(type);
        assertThat(pd.getTitle()).isEqualTo(title);
        assertThat(pd.getStatus()).isEqualTo(status);
        assertThat(pd.getDetail()).isEqualTo(detail);
        assertThat(pd.getInstance()).isEqualTo(instance);
        assertThat(pd.getProperties().get("errorCode")).isEqualTo("VALIDATION_FAILED");
    }

    @Test
    void shouldBuildProblemDetailWithDefaultStatus() {
        ProblemDetail pd = ProblemDetailBuilder.builder().build();

        assertThat(pd.getStatus()).isEqualTo(500);
    }

    @Test
    void shouldChainMethodsCorrectly() {
        ProblemDetail pd = ProblemDetailBuilder.builder()
            .title("Error")
            .status(404)
            .build();

        assertThat(pd.getTitle()).isEqualTo("Error");
        assertThat(pd.getStatus()).isEqualTo(404);
    }

}
