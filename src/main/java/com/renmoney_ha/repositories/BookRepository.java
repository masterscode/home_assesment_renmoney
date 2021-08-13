package com.renmoney_ha.repositories;

import com.renmoney_ha.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    Boolean existsBookByIsbn(String isbn);
}
