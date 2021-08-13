package com.renmoney_ha.controllers;

import com.renmoney_ha.models.Book;
import com.renmoney_ha.payloads.requests.BookRequest;
import com.renmoney_ha.services.BookService;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/v1/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(
                service.getAllBooks()
        );
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable Integer id){
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(@RequestParam("query") String searchQuery){
        return ResponseEntity.ok(
                service.searchBook(searchQuery)
        );
    }

    @GetMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id){
        return null;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookRequest book){
        return ResponseEntity.ok(
                service.addBook(book)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRequest requestBody){
        return ResponseEntity.ok(
                service.updateBook(id, requestBody)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> removeBook(@PathVariable Long id){
        return ResponseEntity.ok(
                service.deleteBook(id)
        );
    }
}
