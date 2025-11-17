package com.desenv.biblioteca.api.controller;

import com.desenv.biblioteca.api.dto.BookDTO;
import com.desenv.biblioteca.application.dto.BookServiceDTO;
import com.desenv.biblioteca.application.service.BookService;
import com.desenv.biblioteca.infra.entity.BookEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookAPI {

    private final BookService bookService;

    public BookAPI(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Objetivo: O objetivo deste metodo é receptar as chamasas
     * para a inclusão de um livro
     */
    @PostMapping("/save")
    public ResponseEntity<String> saveBook(@RequestBody BookDTO book) {
        BookServiceDTO saveServiceReturn = bookService.saveBook(book);

        if (HttpStatus.CREATED.equals(saveServiceReturn.getHttpStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveServiceReturn.getOperationResume());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(saveServiceReturn.getOperationResume());
    }

    /**
     * Objetivo: Consultar todos os livros registrados no sistema
     */
    @GetMapping("/find/all")
    public ResponseEntity getAllBooks() {
        BookServiceDTO getAllServiceReturn = bookService.getAllBooks();

        if (HttpStatus.OK.equals(getAllServiceReturn.getHttpStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(getAllServiceReturn.getBooks());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getAllServiceReturn.getOperationResume());
    }

    /**
     * Objetivo: Consultar um livro com base no seu ID
     */
    @GetMapping("/find/id/{id}")
    public ResponseEntity getBookById(@PathVariable Long id) {
        BookServiceDTO getAllServiceReturn = bookService.getBookById(id);

        if (HttpStatus.OK.equals(getAllServiceReturn.getHttpStatus())) {
            return ResponseEntity.status(HttpStatus.OK).body(getAllServiceReturn.getBooks());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getAllServiceReturn.getOperationResume());
    }

    /**
     * Objetivo: Consultar um livro com base no seu ISBN
     */
    @GetMapping("/find/isbm/{isbm}")
    public ResponseEntity getByISBN(@PathVariable String isbm) {
        BookServiceDTO getAllServiceReturn = bookService.getByISBN(isbm);

        if (HttpStatus.OK.equals(getAllServiceReturn.getHttpStatus())) {
            return ResponseEntity.status(HttpStatus.OK).body(getAllServiceReturn.getBooks());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getAllServiceReturn.getOperationResume());
    }

    /**
     * Objetivo: Atualizar os dados de um livro
     */
    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody BookDTO book) {
        BookServiceDTO updateOperationReturn = bookService.updateBook(book, id);

        if (HttpStatus.OK.equals(updateOperationReturn.getHttpStatus())) {
            return ResponseEntity.status(HttpStatus.OK).body(updateOperationReturn.getBooks());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updateOperationReturn.getOperationResume());
    }

    /**
     * Objetivo: Deletar um livro
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity getByISBN(@PathVariable Long id) {
        BookServiceDTO deleteOperationReturn = bookService.deleteBook(id);

        if (HttpStatus.OK.equals(deleteOperationReturn.getHttpStatus())) {
            return ResponseEntity.status(HttpStatus.OK).body(deleteOperationReturn.getOperationResume());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(deleteOperationReturn.getOperationResume());
    }
}
