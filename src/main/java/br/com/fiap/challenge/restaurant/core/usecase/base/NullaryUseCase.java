package br.com.fiap.challenge.restaurant.core.usecase.base;

public interface NullaryUseCase<O> {

    // Execute the use case without any input and return the output
    O execute();

}
