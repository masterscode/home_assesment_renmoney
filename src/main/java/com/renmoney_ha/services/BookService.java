package com.renmoney_ha.services;


import com.renmoney_ha.payloads.requests.BookRequest;
import org.springframework.http.ResponseEntity;

/**
 * The API will be used to
 * add, update, delete, lend books
 * to users and search for books in the library.
 */
public interface BookService {
    ResponseEntity<?> addBook(BookRequest bookRequest);
    ResponseEntity<?> updateBook(BookRequest bookRequest);
    ResponseEntity<?> deleteBook(BookRequest bookRequest);
    ResponseEntity<?> borrowBook(BookRequest bookRequest);

}
