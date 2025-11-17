package com.desenv.biblioteca.domain.exception;

public class ISBNInvalidException extends BibliotecaException {
    public ISBNInvalidException() {
        super("O número do ISBN não está em um formato válido, verifique e tente novamente.");
    }
}
