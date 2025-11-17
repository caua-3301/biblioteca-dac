package com.desenv.biblioteca.infra.jpa;

import com.desenv.biblioteca.infra.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    public BookEntity findByISBN(String isbn);
}
