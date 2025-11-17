package com.desenv.biblioteca.application.service;

import com.desenv.biblioteca.api.dto.BookDTO;
import com.desenv.biblioteca.application.dto.BookServiceDTO;
import com.desenv.biblioteca.domain.exception.BibliotecaException;
import com.desenv.biblioteca.domain.exception.BookNotFoundException;
import com.desenv.biblioteca.domain.exception.ISBNInvalidException;
import com.desenv.biblioteca.domain.exception.UpdateBookException;
import com.desenv.biblioteca.domain.model.Book;
import com.desenv.biblioteca.infra.entity.BookEntity;
import com.desenv.biblioteca.domain.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/*
* Objetivo: implementar as lógicas necessárias para realizar as operções referentes a
* um livro
* */
@Service
public class BookService {

    private final BookRepository bookRepository;

    // Injetando as dependências necessárias para inclusão
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Realiza o processamento necessário para salvar um livro
    public BookServiceDTO saveBook(BookDTO bookDTO) {

        // O objetivo deste objeto é tranferir os dados do processamento relizado neste service
        BookServiceDTO bookServiceDTO = new BookServiceDTO();

        try {
            // Validando os dados com base nas regras
            validadeBook(bookDTO);

            // Instanciando a entidade Book
            BookEntity bookEntity = new BookEntity();

            // Adicionando os dados informados na requisição a entidade
            bookEntity.setAuthor(bookDTO.title());
            bookEntity.setTitle(bookDTO.author());
            bookEntity.setISBN(bookDTO.ISBN());
            bookEntity.setPublished(bookDTO.publicationDate());
            bookEntity.setInStock(bookDTO.inStock());

            // Adicionando as informações de processamento de inclusão do livro
            Integer bookAddedId = bookRepository.saveBook(bookEntity);

            bookServiceDTO.setHttpStatus(HttpStatus.CREATED);
            bookServiceDTO.setOperationResume("Book ID: " + bookAddedId);

        } catch (Exception exception) {
            // Tratando exceções caso ocorram
            bookServiceDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            if (exception instanceof BibliotecaException) {
                bookServiceDTO.setOperationResume(exception.getMessage());
            } else {
                bookServiceDTO.setOperationResume("Ocorreu um erro durante o processamento, tente novamente");
            }
        }

        return bookServiceDTO;
    }

    // Realiza o processamento necessário para listar todos os livros disponíveis
    public BookServiceDTO getAllBooks() {

        // O objetivo deste objeto é tranferir os dados do processamento relizado neste service
        BookServiceDTO bookServiceDTO = new BookServiceDTO();

        try {

            List<BookEntity> books = bookRepository.getAllBooks();

            // Validando o retorno dos livros
            if (books.isEmpty()) {
                throw new BookNotFoundException();
            }

            bookServiceDTO.setBooks(books);
            bookServiceDTO.setHttpStatus(HttpStatus.OK);
            bookServiceDTO.setOperationResume("Quantidade de livros encontrados: " + books.size());
        } catch (Exception exception) {
            // Tratando exceções caso ocorram
            bookServiceDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            if (exception instanceof BibliotecaException) {
                bookServiceDTO.setOperationResume(exception.getMessage());
            } else {
                bookServiceDTO.setOperationResume("Ocorreu um erro durante o processamento, tente novamente");
            }
        }

        return bookServiceDTO;
    }

    // Realiza o processamento necessário para consultar um livro com base no seu ID
    public BookServiceDTO getBookById(Long bookId) {

        // O objetivo deste objeto é tranferir os dados do processamento relizado neste service
        BookServiceDTO bookServiceDTO = new BookServiceDTO();

        try {

            BookEntity book = bookRepository.getBookById(bookId);

            // Validando o retorno dos livros
            if (book == null) {
                throw new BookNotFoundException();
            }

            bookServiceDTO.setBooks(List.of(book));
            bookServiceDTO.setHttpStatus(HttpStatus.OK);
        } catch (Exception exception) {
            // Tratando exceções caso ocorram
            bookServiceDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            if (exception instanceof BibliotecaException) {
                bookServiceDTO.setOperationResume(exception.getMessage());
            } else {
                bookServiceDTO.setOperationResume("Ocorreu um erro durante o processamento, tente novamente");
            }
        }

        return bookServiceDTO;
    }

    // Realiza o processamento necessário para consultar um livro com base no seu ISBN
    public BookServiceDTO getByISBN(String isbn) {

        // O objetivo deste objeto é tranferir os dados do processamento relizado neste service
        BookServiceDTO bookServiceDTO = new BookServiceDTO();

        try {

            BookEntity book = bookRepository.getBookByISBN(isbn);

            // Validando o retorno dos livros
            if (book == null) {
                throw new BookNotFoundException();
            }

            bookServiceDTO.setBooks(List.of(book));
            bookServiceDTO.setHttpStatus(HttpStatus.OK);
        } catch (Exception exception) {
            // Tratando exceções caso ocorram
            bookServiceDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            if (exception instanceof BibliotecaException) {
                bookServiceDTO.setOperationResume(exception.getMessage());
            } else {
                bookServiceDTO.setOperationResume("Ocorreu um erro durante o processamento, tente novamente");
            }
        }

        return bookServiceDTO;
    }

    // Realiza o processamento necessário para alterar um livro
    public BookServiceDTO updateBook(BookDTO bookDTO, Long bookId) {

        // O objetivo deste objeto é tranferir os dados do processamento relizado neste service
        BookServiceDTO bookServiceDTO = new BookServiceDTO();

        try {
            // Validando os dados com base nas regras
            validadeBook(bookDTO);

            // Instanciando a entidade Book
            BookServiceDTO serviceDTO = this.getBookById(bookId);

            if (HttpStatus.INTERNAL_SERVER_ERROR.equals(serviceDTO.getHttpStatus())) {
                throw new BookNotFoundException();
            }

            BookEntity bookEntity = serviceDTO.getBooks().get(0);

            // Validando alteração
            validateBookUpdate(bookDTO);

            // Adicionando os dados informados na requisição a entidade
            if (bookDTO.author() != null) {
                bookEntity.setAuthor(bookDTO.author());
            }

            if (bookDTO.title() != null) {
                bookEntity.setTitle(bookDTO.title());
            }

            if (bookDTO.publicationDate() != null) {
                bookEntity.setPublished(bookDTO.publicationDate());
            }

            // Adicionando as informações de processamento de inclusão do livro
            Integer bookAddedId = bookRepository.saveBook(bookEntity);

            bookServiceDTO.setHttpStatus(HttpStatus.OK);
            bookServiceDTO.setBooks(List.of(bookEntity));

        } catch (Exception exception) {
            // Tratando exceções caso ocorram
            bookServiceDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            if (exception instanceof BibliotecaException) {
                bookServiceDTO.setOperationResume(exception.getMessage());
            } else {
                bookServiceDTO.setOperationResume("Ocorreu um erro durante o processamento, tente novamente");
            }
        }

        return bookServiceDTO;
    }

    // Realiza o processamento necessário para alterar um livro
    public BookServiceDTO deleteBook(Long bookId) {

        // O objetivo deste objeto é tranferir os dados do processamento relizado neste service
        BookServiceDTO bookServiceDTO = new BookServiceDTO();

        try {

            // Instanciando a entidade Book
            BookServiceDTO serviceDTO = this.getBookById(bookId);

            if (HttpStatus.INTERNAL_SERVER_ERROR.equals(serviceDTO.getHttpStatus())) {
                throw new BookNotFoundException();
            }

            BookEntity bookEntity = serviceDTO.getBooks().get(0);

            bookRepository.removeBook(bookEntity);

            bookServiceDTO.setHttpStatus(HttpStatus.OK);
            bookServiceDTO.setOperationResume("Livro removido com sucesso");

        } catch (Exception exception) {
            // Tratando exceções caso ocorram
            bookServiceDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            if (exception instanceof BibliotecaException) {
                bookServiceDTO.setOperationResume(exception.getMessage());
            } else {
                bookServiceDTO.setOperationResume("Ocorreu um erro durante o processamento, tente novamente");
            }
        }

        return bookServiceDTO;
    }

    private void validadeBook(BookDTO bookDTO) throws ISBNInvalidException {
        Book book = new Book(bookDTO.title(), bookDTO.author(), bookDTO.ISBN(), bookDTO.publicationDate());

        book.validateBookPersistence();
    }

    private void validateBookUpdate(BookDTO bookDTO) throws UpdateBookException, ISBNInvalidException {
        Book book = new Book(bookDTO.title(), bookDTO.author(), bookDTO.ISBN(), bookDTO.publicationDate());

        book.validateBookUpdate();
    }
}
