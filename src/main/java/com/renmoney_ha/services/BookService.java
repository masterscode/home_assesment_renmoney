package com.renmoney_ha.services;


import com.renmoney_ha.models.Book;
import com.renmoney_ha.payloads.requests.BookRequest;

import java.util.List;

/**
 * The API will be used to
 * add, update, delete, lend books
 * to users and search for books in the library.
 */
public interface BookService {
    Book addBook(BookRequest bookRequest);
    Book updateBook(Long id, BookRequest request);
    Book deleteBook(Long id);
    Book borrowBook(BookRequest bookRequest);
    List<Book> searchBook(String searchQuery);
    List<Book> getAllBooks();

}
