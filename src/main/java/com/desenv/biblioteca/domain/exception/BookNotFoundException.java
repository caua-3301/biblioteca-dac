package com.desenv.biblioteca.domain.exception;

public class BookNotFoundException extends BibliotecaException {
    public BookNotFoundException() {
        super("Não existem livros cadastrados para os dados informados.");
    }
}
