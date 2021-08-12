package com.renmoney_ha.controllers;

import com.renmoney_ha.payloads.requests.BookRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/v1/books")
public class BookController {

    @GetMapping
    public String getAllBooks(){
        return "searchQuery";
    }

    @GetMapping("/{bookId}")
    public String getBook(@PathVariable Integer bookId){
        return "searchQuery";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String searchQuery){
        return searchQuery;
    }

    @GetMapping("/borrow/{bookId}")
    public String borrowBook(@PathVariable Integer bookId){
        return "searchQuery";
    }

    @PostMapping
    public String addBook(@RequestBody BookRequest book){
        return "";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable Integer id){
        return "";
    }
    @DeleteMapping("/{id}")
    public String removeBook(@PathVariable Integer id){
        return "";
    }
}
