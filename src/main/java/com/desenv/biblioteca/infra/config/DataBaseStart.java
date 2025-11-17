package com.desenv.biblioteca.infra.config;

import com.desenv.biblioteca.domain.repository.BookRepository;
import com.desenv.biblioteca.infra.entity.BookEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseStart implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataBaseStart(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /*
    * O objetivo deste metodo é povoar o banco com alguns dados no início da aplicação
    * */
    @Override
    public void run(String... args) throws Exception {
        BookEntity bookOne = new BookEntity();
        bookOne.setTitle("Clean Code");
        bookOne.setAuthor("Robert C. Martin");
        bookOne.setISBN("978-0-13-235088-4");
        bookOne.setPublished(java.sql.Date.valueOf("2008-08-01"));
        bookOne.setInStock(12L);

        BookEntity bookTwo = new BookEntity();
        bookTwo.setTitle("Effective Java");
        bookTwo.setAuthor("Joshua Bloch");
        bookTwo.setISBN("978-0-13-468599-1");
        bookTwo.setPublished(java.sql.Date.valueOf("2018-01-06"));
        bookTwo.setInStock(8L);

        BookEntity bookThree = new BookEntity();
        bookThree.setTitle("Java: The Complete Reference");
        bookThree.setAuthor("Herbert Schildt");
        bookThree.setISBN("978-1-260-44893-3");
        bookThree.setPublished(java.sql.Date.valueOf("2022-05-15"));
        bookThree.setInStock(20L);

        BookEntity bookFour = new BookEntity();
        bookFour.setTitle("Spring in Action");
        bookFour.setAuthor("Craig Walls");
        bookFour.setISBN("978-1-61729-494-5");
        bookFour.setPublished(java.sql.Date.valueOf("2018-10-01"));
        bookFour.setInStock(5L);

        bookRepository.saveBook(bookOne);
        bookRepository.saveBook(bookTwo);
        bookRepository.saveBook(bookThree);
        bookRepository.saveBook(bookFour);
    }
}
