package br.com.fiap.challenge.restautant.core.usecase.base;

public interface UseCase<P, R> {
    R execute(P parameter);
}
