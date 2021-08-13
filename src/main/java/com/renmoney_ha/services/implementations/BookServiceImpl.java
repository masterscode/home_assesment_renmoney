package com.renmoney_ha.services.implementations;

import com.renmoney_ha.models.Book;
import com.renmoney_ha.payloads.requests.BookRequest;
import com.renmoney_ha.repositories.BookRepository;
import com.renmoney_ha.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository repository;

    @Autowired
    Validator validator;

    private final ModelMapper  modelmapper = new ModelMapper();

    @Override
    public Book addBook(BookRequest request) {
        validateRequestBody(request);
        Boolean bookExists = repository.existsBookByIsbn(request.getIsbn());
        if (Boolean.FALSE.equals(bookExists)) throw new BadCredentialsException("");
        Book newBook = modelmapper.map(request, Book.class);
        return repository.save(newBook);
    }

    @Override
    public Book updateBook(Long id, BookRequest request) {
        validateRequestBody(request);
        Book bookToUpdate = repository.findById(id).orElseThrow();
        Book updatedBook = modelmapper.map(request, Book.class);
        updatedBook.setId(bookToUpdate.getId());
        return repository.save(updatedBook);
    }

    @Override
    public Book deleteBook(Long id) {
        Book bookToDelete = repository.findById(id).orElseThrow();
        bookToDelete.setIsDeleted(true);
        return repository.save(bookToDelete);
    }

    @Override
    public Book borrowBook(BookRequest bookRequest) {
        return null;
    }

    @Override
    public List<Book> searchBook(String query) {
       return repository.findAll()
                .stream()
                .filter(book ->
                        book.getTitle().contains(query)
                        || book.getAuthor().contains(query)
                        && !book.getIsDeleted()
                ).toList();
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll()
                .stream()
                .filter(book -> !book.getIsDeleted())
                .toList();
    }

    private void validateRequestBody(BookRequest request){
        Collection<ConstraintViolation<BookRequest>> result = validator.validate(request);
        if (!result.isEmpty()) throw new BadCredentialsException("");
    }
}
