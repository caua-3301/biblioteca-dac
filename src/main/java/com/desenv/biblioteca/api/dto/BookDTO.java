package com.desenv.biblioteca.api.dto;

import java.util.Date;

/*
* Record qie auxiliará no transporte de dados para efetuar as operações das entidades de "Book"
* */
public record BookDTO(String title, String author, String ISBN, Date publicationDate, Long inStock) { }