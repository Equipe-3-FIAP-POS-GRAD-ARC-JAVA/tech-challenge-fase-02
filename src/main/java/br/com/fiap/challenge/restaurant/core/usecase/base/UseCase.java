package br.com.fiap.challenge.restaurant.core.usecase.base;

public interface UseCase<P, R> {
    R execute(P parameter);
}
