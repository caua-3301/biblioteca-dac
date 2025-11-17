package com.desenv.biblioteca.domain.exception;

public class UpdateBookException extends BibliotecaException {
    public UpdateBookException(String message) {
        super("Não foi possível atualizar o livro: " + message);
    }
}
