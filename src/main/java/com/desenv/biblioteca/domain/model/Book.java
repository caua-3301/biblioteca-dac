package com.desenv.biblioteca.domain.model;

import com.desenv.biblioteca.domain.exception.ISBNInvalidException;
import com.desenv.biblioteca.domain.exception.UpdateBookException;

import java.util.Date;
import java.util.regex.Pattern;

public class Book {

    private String title;

    private String author;

    private String ISBN;

    private Date published;

    public Book(String title, String author, String ISBN, Date published) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.published = published;
    }

    // O objetivo desta constante é definir a forma do número do ISBN
    private static final Pattern ISBN_REGEX = Pattern.compile(
            "^(?:\\d[\\- ]?){9}[\\dX]$|^97[89][\\- ]?\\d([\\- ]?\\d){9}$"
    );

    /**
     * Objetivo: verificar as regras antes de incluir um livro
     * */
    public void validateBookPersistence() throws ISBNInvalidException {
        // Validando o número ISBN informado
        if (ISBN != null && !ISBN_REGEX.matcher(ISBN).matches()) {
            throw new ISBNInvalidException();
        }
    }

    /**
     * Objetivo: verificar as regras antes de atualizar um livro
     * */
    public void validateBookUpdate() throws ISBNInvalidException, UpdateBookException {
        if (this.ISBN != null && !this.ISBN.isEmpty()) {
            throw new UpdateBookException("O valor do ISBN não pode ser alterado");
        }
    }
}
