package com.renmoney_ha.services.implementations;

import com.renmoney_ha.models.Book;
import com.renmoney_ha.models.BorrowedBooks;
import com.renmoney_ha.models.User;
import com.renmoney_ha.payloads.requests.BookRequest;
import com.renmoney_ha.payloads.requests.BorrowBookRequest;
import com.renmoney_ha.repositories.BookRepository;
import com.renmoney_ha.repositories.BorrowedBooksRepository;
import com.renmoney_ha.repositories.UserRepository;
import com.renmoney_ha.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final ModelMapper modelmapper = new ModelMapper();
    @Autowired
    BookRepository repository;
    @Autowired
    Validator validator;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BorrowedBooksRepository borrowedBooksRepository;

    @Override
    public Book addBook(BookRequest request) {
        Boolean bookExists = repository.existsBookByIsbn(request.getIsbn());
        if (Boolean.TRUE.equals(bookExists)) throw new EntityExistsException();
        Book newBook = modelmapper.map(request, Book.class);
        return repository.save(newBook);
    }

    @Override
    public Book updateBook(Long id, BookRequest request) {
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
    @Transactional
    public List<Book> borrowBook(BorrowBookRequest borrowRequest) {
        BorrowedBooks borrow = new BorrowedBooks();
        User borrower = userRepository.findById(borrowRequest.getRecipientUserId()).orElseThrow();
        List<Book> booksToBorrow = repository
                .findAllById(borrowRequest.getBookId())
                .stream()
                .filter(book -> !book.getIsDeleted() && !book.getIsBorrowed())
                .toList();

        borrow.setUser(borrower);
        borrow.setBooks(booksToBorrow);
        borrow.setBorrowDate(LocalDate.now());
        borrowedBooksRepository.save(borrow);

        booksToBorrow.forEach(book -> book.setIsBorrowed(true));
        repository.saveAll(booksToBorrow);


        return booksToBorrow;
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

    private void validateRequestBody(BookRequest request) {
        Collection<ConstraintViolation<BookRequest>> result = validator.validate(request);
        if (!result.isEmpty()) throw new ValidationException();
    }
}
