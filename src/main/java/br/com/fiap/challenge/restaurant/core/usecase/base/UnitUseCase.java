package br.com.fiap.challenge.restaurant.core.usecase.base;

public interface UnitUseCase<I> {

    // Use case execution method that receives input and returns no output.
    void execute(I input);

}
