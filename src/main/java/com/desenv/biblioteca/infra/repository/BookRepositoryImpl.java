package com.desenv.biblioteca.infra.repository;

import com.desenv.biblioteca.api.dto.BookDTO;
import com.desenv.biblioteca.infra.entity.BookEntity;
import com.desenv.biblioteca.domain.repository.BookRepository;
import com.desenv.biblioteca.infra.jpa.BookJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final BookJpaRepository bookJpaRepository;

    // Injetando o repositório JPA
    public BookRepositoryImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    /*
    * Serviço responsável pela persistência de um livro na base de dados
    * */
    @Override
    public Integer saveBook(BookEntity book) {
        // Obetendo o Book persistido e retornado o ID a ele atribuido
        BookEntity bookAdded = bookJpaRepository.save(book);
        return bookAdded.getId().intValue();
    }

    /*
     * Serviço responsável pela consulta de todos os livro na base de dados
     * */
    @Override
    public List<BookEntity> getAllBooks() {
        List<BookEntity> bookEntities = bookJpaRepository.findAll();
        return bookEntities;
    }

    /*
     * Serviço responsável pela consulta de um livro com base no ID
     * */
    @Override
    public BookEntity getBookById(Long id) {
        BookEntity book = bookJpaRepository.findById(id).orElse(null);
        return book;
    }

    /*
     * Serviço responsável pela consulta de um livro com base no ISBN
     * */
    @Override
    public BookEntity getBookByISBN(String ISBN) {
        BookEntity bookEntity = bookJpaRepository.findByISBN(ISBN);
        return bookEntity;
    }

    @Override
    public void removeBook(BookEntity book) {
        bookJpaRepository.delete(book);
    }
}
