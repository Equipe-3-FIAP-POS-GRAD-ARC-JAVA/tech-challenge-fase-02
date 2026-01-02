package br.com.fiap.challenge.restautant.infra.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fiap.challenge.restautant.core.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        return ProblemDetailBuilder.builder()
                .title("Resource Not Found")
                .detail(ex.getMessage())
                .status(404)
                .build();
    }
}
