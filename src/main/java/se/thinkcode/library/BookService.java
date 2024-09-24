package se.thinkcode.library;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.create(book);
    }

    public List<Book> searchBooks(Title title) {
        return bookRepository.searchBooks(title);
    }

    public Book searchBooks(ISBN isbn) {
        return bookRepository.searchBooks(isbn);
    }

    public List<Book> searchByAuthor(String surname) {
        return bookRepository.searchByAuthor(surname);
    }

    public List<Book> searchByFirstName(String firstName) {
        return bookRepository.searchByFirstName(firstName);
    }

}
