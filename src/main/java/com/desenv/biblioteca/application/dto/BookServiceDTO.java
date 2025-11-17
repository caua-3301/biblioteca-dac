package com.desenv.biblioteca.application.dto;

import com.desenv.biblioteca.api.dto.BookDTO;
import com.desenv.biblioteca.infra.entity.BookEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Objetivo: Transportar dados entre as camadas de service e controller
 */
public class BookServiceDTO {

    private String operationResume;

    private HttpStatus httpStatus;

    private List<BookEntity> books;

    public BookServiceDTO(String operationResume, HttpStatus httpStatus, List<BookEntity> books) {
        this.operationResume = operationResume;
        this.httpStatus = httpStatus;
        this.books = books;
    }

    public BookServiceDTO() {
    }

    public String getOperationResume() {
        return operationResume;
    }

    public void setOperationResume(String operationResume) {
        this.operationResume = operationResume;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
