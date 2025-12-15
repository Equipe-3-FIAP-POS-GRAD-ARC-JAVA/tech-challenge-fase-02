package br.com.fiap.challenge.restautant.core.usecase;

public interface NullaryUseCase<O> {

    // Execute the use case without any input and return the output
    O execute();

}
