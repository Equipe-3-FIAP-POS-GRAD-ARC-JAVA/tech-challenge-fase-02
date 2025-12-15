package br.com.fiap.challenge.restautant.core.usecase;

public interface UseCase<P, R> {
    R execute(P parameter);
}
