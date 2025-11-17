package com.desenv.biblioteca.infra.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/*Objetivo: Mapear a entidade "Book" para uma tabela na base de dados*/
@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)
    private String ISBN;

    @Column(nullable = false)
    private Date published;

    @Column(nullable = false)
    private Long inStock;

    public BookEntity(Long id, String title, String author, String ISBN, Date published, Long inStock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.published = published;
        this.inStock = inStock;
    }

    public BookEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Long getInStock() {
        return inStock;
    }

    public void setInStock(Long inStock) {
        this.inStock = inStock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity book = (BookEntity) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
