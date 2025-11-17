package com.desenv.biblioteca.domain.repository;

import com.desenv.biblioteca.api.dto.BookDTO;
import com.desenv.biblioteca.infra.entity.BookEntity;

import java.util.List;

/*
* Objetivo: Permitir manipular as operações a nível de banco de dados
* com relação a entidade Book
* */
public interface BookRepository {

    public Integer saveBook(BookEntity book);

    public List<BookEntity> getAllBooks();

    public BookEntity getBookById(Long id);

    public BookEntity getBookByISBN(String ISBN);

    public void removeBook(BookEntity book);
}
