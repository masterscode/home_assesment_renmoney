package com.renmoney_ha.services.implementations;

import com.renmoney_ha.models.Book;
import com.renmoney_ha.models.BorrowedBooks;
import com.renmoney_ha.models.Role;
import com.renmoney_ha.models.User;
import com.renmoney_ha.payloads.requests.BookRequest;
import com.renmoney_ha.payloads.requests.BorrowBookRequest;
import com.renmoney_ha.repositories.BookRepository;
import com.renmoney_ha.repositories.BorrowedBooksRepository;
import com.renmoney_ha.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validator;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    private BookServiceImpl bookServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        bookServiceImplUnderTest = new BookServiceImpl();
        bookServiceImplUnderTest.repository = mock(BookRepository.class);
        bookServiceImplUnderTest.validator = mock(Validator.class);
        bookServiceImplUnderTest.userRepository = mock(UserRepository.class);
        bookServiceImplUnderTest.borrowedBooksRepository = mock(BorrowedBooksRepository.class);
    }

    @Test
    void testAddBook() {
        // Setup
        final BookRequest request = new BookRequest("title", "isbn", "author", "publisher", "language", LocalDate.of(2020, 1, 1), 0, 0.0);
        when(bookServiceImplUnderTest.validator.validate(any(BookRequest.class), eq(Class.class))).thenReturn(Set.of());
        when(bookServiceImplUnderTest.repository.existsBookByIsbn("isbn")).thenReturn(false);

        // Configure BookRepository.save(...).
        final Book book = new Book();
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setIsDeleted(false);
        book.setIsBorrowed(false);
        book.setAuthor("author");
        book.setPublisher("publisher");
        book.setLanguage("language");
        book.setPublicationDate(LocalDate.of(2020, 1, 1));
        book.setPages(0L);
        book.setPrice(0.0);
        when(bookServiceImplUnderTest.repository.save(any(Book.class))).thenReturn(book);

        // Run the test
        final Book result = bookServiceImplUnderTest.addBook(request);

        // Verify the results
    }

    @Test
    void testAddBook_ValidatorReturnsNoItems() {
        // Setup
        final BookRequest request = new BookRequest("title", "isbn", "author", "publisher", "language", LocalDate.of(2020, 1, 1), 0, 0.0);
        when(bookServiceImplUnderTest.validator.validate(any(BookRequest.class), eq(Class.class))).thenReturn(Collections.emptySet());
        when(bookServiceImplUnderTest.repository.existsBookByIsbn("isbn")).thenReturn(false);

        // Configure BookRepository.save(...).
        final Book book = new Book();
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setIsDeleted(false);
        book.setIsBorrowed(false);
        book.setAuthor("author");
        book.setPublisher("publisher");
        book.setLanguage("language");
        book.setPublicationDate(LocalDate.of(2020, 1, 1));
        book.setPages(0L);
        book.setPrice(0.0);
        when(bookServiceImplUnderTest.repository.save(any(Book.class))).thenReturn(book);

        // Run the test
        final Book result = bookServiceImplUnderTest.addBook(request);

        // Verify the results
    }

    @Test
    void testUpdateBook() {
        // Setup
        final BookRequest request = new BookRequest("title", "isbn", "author", "publisher", "language", LocalDate.of(2020, 1, 1), 0, 0.0);
        when(bookServiceImplUnderTest.validator.validate(any(BookRequest.class), eq(Class.class))).thenReturn(Set.of());

        // Configure BookRepository.findById(...).
        final Book book1 = new Book();
        book1.setTitle("title");
        book1.setIsbn("isbn");
        book1.setIsDeleted(false);
        book1.setIsBorrowed(false);
        book1.setAuthor("author");
        book1.setPublisher("publisher");
        book1.setLanguage("language");
        book1.setPublicationDate(LocalDate.of(2020, 1, 1));
        book1.setPages(0L);
        book1.setPrice(0.0);
        final Optional<Book> book = Optional.of(book1);
        when(bookServiceImplUnderTest.repository.findById(0L)).thenReturn(book);

        // Configure BookRepository.save(...).
        final Book book2 = new Book();
        book2.setTitle("title");
        book2.setIsbn("isbn");
        book2.setIsDeleted(false);
        book2.setIsBorrowed(false);
        book2.setAuthor("author");
        book2.setPublisher("publisher");
        book2.setLanguage("language");
        book2.setPublicationDate(LocalDate.of(2020, 1, 1));
        book2.setPages(0L);
        book2.setPrice(0.0);
        when(bookServiceImplUnderTest.repository.save(any(Book.class))).thenReturn(book2);

        // Run the test
        final Book result = bookServiceImplUnderTest.updateBook(0L, request);

        // Verify the results
    }

    @Test
    void testUpdateBook_ValidatorReturnsNoItems() {
        // Setup
        final BookRequest request = new BookRequest("title", "isbn", "author", "publisher", "language", LocalDate.of(2020, 1, 1), 0, 0.0);
        when(bookServiceImplUnderTest.validator.validate(any(BookRequest.class), eq(Class.class))).thenReturn(Collections.emptySet());

        // Configure BookRepository.findById(...).
        final Book book1 = new Book();
        book1.setTitle("title");
        book1.setIsbn("isbn");
        book1.setIsDeleted(false);
        book1.setIsBorrowed(false);
        book1.setAuthor("author");
        book1.setPublisher("publisher");
        book1.setLanguage("language");
        book1.setPublicationDate(LocalDate.of(2020, 1, 1));
        book1.setPages(0L);
        book1.setPrice(0.0);
        final Optional<Book> book = Optional.of(book1);
        when(bookServiceImplUnderTest.repository.findById(0L)).thenReturn(book);

        // Configure BookRepository.save(...).
        final Book book2 = new Book();
        book2.setTitle("title");
        book2.setIsbn("isbn");
        book2.setIsDeleted(false);
        book2.setIsBorrowed(false);
        book2.setAuthor("author");
        book2.setPublisher("publisher");
        book2.setLanguage("language");
        book2.setPublicationDate(LocalDate.of(2020, 1, 1));
        book2.setPages(0L);
        book2.setPrice(0.0);
        when(bookServiceImplUnderTest.repository.save(any(Book.class))).thenReturn(book2);

        // Run the test
        final Book result = bookServiceImplUnderTest.updateBook(0L, request);

        // Verify the results
    }


    @Test
    void testDeleteBook() {
        // Setup

        // Configure BookRepository.findById(...).
        final Book book1 = new Book();
        book1.setTitle("title");
        book1.setIsbn("isbn");
        book1.setIsDeleted(false);
        book1.setIsBorrowed(false);
        book1.setAuthor("author");
        book1.setPublisher("publisher");
        book1.setLanguage("language");
        book1.setPublicationDate(LocalDate.of(2020, 1, 1));
        book1.setPages(0L);
        book1.setPrice(0.0);
        final Optional<Book> book = Optional.of(book1);
        when(bookServiceImplUnderTest.repository.findById(0L)).thenReturn(book);

        // Configure BookRepository.save(...).
        final Book book2 = new Book();
        book2.setTitle("title");
        book2.setIsbn("isbn");
        book2.setIsDeleted(false);
        book2.setIsBorrowed(false);
        book2.setAuthor("author");
        book2.setPublisher("publisher");
        book2.setLanguage("language");
        book2.setPublicationDate(LocalDate.of(2020, 1, 1));
        book2.setPages(0L);
        book2.setPrice(0.0);
        when(bookServiceImplUnderTest.repository.save(any(Book.class))).thenReturn(book2);

        // Run the test
        final Book result = bookServiceImplUnderTest.deleteBook(0L);

        // Verify the results
    }


    @Test
    void testBorrowBook() {
        // Setup
        final BorrowBookRequest borrowRequest = new BorrowBookRequest(List.of(0L), 0L);

        // Configure UserRepository.findById(...).
        final User user1 = new User();
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setIsAccountNonExpired(false);
        user1.setIsAccountNonLocked(false);
        user1.setIsCredentialsNonExpired(false);
        user1.setIsEnabled(false);
        final Role role = new Role();
        user1.setRoles(List.of(role));
        final Optional<User> user = Optional.of(user1);
        when(bookServiceImplUnderTest.userRepository.findById(0L)).thenReturn(user);

        // Configure BookRepository.findAllById(...).
        final Book book = new Book();
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setIsDeleted(false);
        book.setIsBorrowed(false);
        book.setAuthor("author");
        book.setPublisher("publisher");
        book.setLanguage("language");
        book.setPublicationDate(LocalDate.of(2020, 1, 1));
        book.setPages(0L);
        book.setPrice(0.0);
        final List<Book> books = List.of(book);
        when(bookServiceImplUnderTest.repository.findAllById(List.of(0L))).thenReturn(books);

        // Configure BorrowedBooksRepository.save(...).
        final BorrowedBooks borrowedBooks = new BorrowedBooks();
        final Book book1 = new Book();
        book1.setTitle("title");
        book1.setIsbn("isbn");
        book1.setIsDeleted(false);
        book1.setIsBorrowed(false);
        book1.setAuthor("author");
        book1.setPublisher("publisher");
        book1.setLanguage("language");
        book1.setPublicationDate(LocalDate.of(2020, 1, 1));
        book1.setPages(0L);
        book1.setPrice(0.0);
        borrowedBooks.setBooks(List.of(book1));
        final User user2 = new User();
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setIsAccountNonExpired(false);
        user2.setIsAccountNonLocked(false);
        user2.setIsCredentialsNonExpired(false);
        user2.setIsEnabled(false);
        final Role role1 = new Role();
        user2.setRoles(List.of(role1));
        borrowedBooks.setUser(user2);
        borrowedBooks.setReturnedDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        borrowedBooks.setBorrowDate(LocalDate.of(2020, 1, 1));
        when(bookServiceImplUnderTest.borrowedBooksRepository.save(any(BorrowedBooks.class))).thenReturn(borrowedBooks);

        // Configure BookRepository.saveAll(...).
        final Book book2 = new Book();
        book2.setTitle("title");
        book2.setIsbn("isbn");
        book2.setIsDeleted(false);
        book2.setIsBorrowed(false);
        book2.setAuthor("author");
        book2.setPublisher("publisher");
        book2.setLanguage("language");
        book2.setPublicationDate(LocalDate.of(2020, 1, 1));
        book2.setPages(0L);
        book2.setPrice(0.0);
        final List<Book> books1 = List.of(book2);
        when(bookServiceImplUnderTest.repository.saveAll(List.of(new Book()))).thenReturn(books1);

        // Run the test
        final List<Book> result = bookServiceImplUnderTest.borrowBook(borrowRequest);

        // Verify the results
    }


    @Test
    void testBorrowBook_BookRepositoryFindAllByIdReturnsNoItems() {
        // Setup
        final BorrowBookRequest borrowRequest = new BorrowBookRequest(List.of(0L), 0L);

        // Configure UserRepository.findById(...).
        final User user1 = new User();
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setIsAccountNonExpired(false);
        user1.setIsAccountNonLocked(false);
        user1.setIsCredentialsNonExpired(false);
        user1.setIsEnabled(false);
        final Role role = new Role();
        user1.setRoles(List.of(role));
        final Optional<User> user = Optional.of(user1);
        when(bookServiceImplUnderTest.userRepository.findById(0L)).thenReturn(user);

        when(bookServiceImplUnderTest.repository.findAllById(List.of(0L))).thenReturn(Collections.emptyList());

        // Configure BorrowedBooksRepository.save(...).
        final BorrowedBooks borrowedBooks = new BorrowedBooks();
        final Book book = new Book();
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setIsDeleted(false);
        book.setIsBorrowed(false);
        book.setAuthor("author");
        book.setPublisher("publisher");
        book.setLanguage("language");
        book.setPublicationDate(LocalDate.of(2020, 1, 1));
        book.setPages(0L);
        book.setPrice(0.0);
        borrowedBooks.setBooks(List.of(book));
        final User user2 = new User();
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setIsAccountNonExpired(false);
        user2.setIsAccountNonLocked(false);
        user2.setIsCredentialsNonExpired(false);
        user2.setIsEnabled(false);
        final Role role1 = new Role();
        user2.setRoles(List.of(role1));
        borrowedBooks.setUser(user2);
        borrowedBooks.setReturnedDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        borrowedBooks.setBorrowDate(LocalDate.of(2020, 1, 1));
        when(bookServiceImplUnderTest.borrowedBooksRepository.save(any(BorrowedBooks.class))).thenReturn(borrowedBooks);

        // Configure BookRepository.saveAll(...).
        final Book book1 = new Book();
        book1.setTitle("title");
        book1.setIsbn("isbn");
        book1.setIsDeleted(false);
        book1.setIsBorrowed(false);
        book1.setAuthor("author");
        book1.setPublisher("publisher");
        book1.setLanguage("language");
        book1.setPublicationDate(LocalDate.of(2020, 1, 1));
        book1.setPages(0L);
        book1.setPrice(0.0);
        final List<Book> books = List.of(book1);
        when(bookServiceImplUnderTest.repository.saveAll(List.of(new Book()))).thenReturn(books);

        // Run the test
        final List<Book> result = bookServiceImplUnderTest.borrowBook(borrowRequest);

        // Verify the results
    }

    @Test
    void testBorrowBook_BookRepositorySaveAllReturnsNoItems() {
        // Setup
        final BorrowBookRequest borrowRequest = new BorrowBookRequest(List.of(0L), 0L);

        // Configure UserRepository.findById(...).
        final User user1 = new User();
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setIsAccountNonExpired(false);
        user1.setIsAccountNonLocked(false);
        user1.setIsCredentialsNonExpired(false);
        user1.setIsEnabled(false);
        final Role role = new Role();
        user1.setRoles(List.of(role));
        final Optional<User> user = Optional.of(user1);
        when(bookServiceImplUnderTest.userRepository.findById(0L)).thenReturn(user);

        // Configure BookRepository.findAllById(...).
        final Book book = new Book();
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setIsDeleted(false);
        book.setIsBorrowed(false);
        book.setAuthor("author");
        book.setPublisher("publisher");
        book.setLanguage("language");
        book.setPublicationDate(LocalDate.of(2020, 1, 1));
        book.setPages(0L);
        book.setPrice(0.0);
        final List<Book> books = List.of(book);
        when(bookServiceImplUnderTest.repository.findAllById(List.of(0L))).thenReturn(books);

        // Configure BorrowedBooksRepository.save(...).
        final BorrowedBooks borrowedBooks = new BorrowedBooks();
        final Book book1 = new Book();
        book1.setTitle("title");
        book1.setIsbn("isbn");
        book1.setIsDeleted(false);
        book1.setIsBorrowed(false);
        book1.setAuthor("author");
        book1.setPublisher("publisher");
        book1.setLanguage("language");
        book1.setPublicationDate(LocalDate.of(2020, 1, 1));
        book1.setPages(0L);
        book1.setPrice(0.0);
        borrowedBooks.setBooks(List.of(book1));
        final User user2 = new User();
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setIsAccountNonExpired(false);
        user2.setIsAccountNonLocked(false);
        user2.setIsCredentialsNonExpired(false);
        user2.setIsEnabled(false);
        final Role role1 = new Role();
        user2.setRoles(List.of(role1));
        borrowedBooks.setUser(user2);
        borrowedBooks.setReturnedDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        borrowedBooks.setBorrowDate(LocalDate.of(2020, 1, 1));
        when(bookServiceImplUnderTest.borrowedBooksRepository.save(any(BorrowedBooks.class))).thenReturn(borrowedBooks);

        when(bookServiceImplUnderTest.repository.saveAll(List.of(new Book()))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Book> result = bookServiceImplUnderTest.borrowBook(borrowRequest);

        // Verify the results
    }

    @Test
    void testSearchBook() {
        // Setup

        // Configure BookRepository.findAll(...).
        final Book book = new Book();
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setIsDeleted(false);
        book.setIsBorrowed(false);
        book.setAuthor("author");
        book.setPublisher("publisher");
        book.setLanguage("language");
        book.setPublicationDate(LocalDate.of(2020, 1, 1));
        book.setPages(0L);
        book.setPrice(0.0);
        final List<Book> books = List.of(book);
        when(bookServiceImplUnderTest.repository.findAll()).thenReturn(books);

        // Run the test
        final List<Book> result = bookServiceImplUnderTest.searchBook("query");

        // Verify the results
    }

    @Test
    void testSearchBook_BookRepositoryReturnsNoItems() {
        // Setup
        when(bookServiceImplUnderTest.repository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Book> result = bookServiceImplUnderTest.searchBook("query");

        // Verify the results
    }

    @Test
    void testGetAllBooks() {
        // Setup

        // Configure BookRepository.findAll(...).
        final Book book = new Book();
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setIsDeleted(false);
        book.setIsBorrowed(false);
        book.setAuthor("author");
        book.setPublisher("publisher");
        book.setLanguage("language");
        book.setPublicationDate(LocalDate.of(2020, 1, 1));
        book.setPages(0L);
        book.setPrice(0.0);
        final List<Book> books = List.of(book);
        when(bookServiceImplUnderTest.repository.findAll()).thenReturn(books);

        // Run the test
        final List<Book> result = bookServiceImplUnderTest.getAllBooks();

        // Verify the results
    }

    @Test
    void testGetAllBooks_BookRepositoryReturnsNoItems() {
        // Setup
        when(bookServiceImplUnderTest.repository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Book> result = bookServiceImplUnderTest.getAllBooks();

        // Verify the results
    }
}
