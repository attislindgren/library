package se.thinkcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final Map<Borrower, List<Book>> checkedOutBooks = new HashMap<>();


    public Library(BookRepository bookRepository, BorrowerRepository borrowerRepository) {

        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
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

    public Borrower searchBorrower(FirstName name) {
        return borrowerRepository.searchBorrower(name);
    }

    public void borrowBook(Book book, Borrower borrower) {
        List<Book> bookList = this.checkedOutBooks.getOrDefault(borrower, new ArrayList<>());
        bookList.add(book);
        this.checkedOutBooks.put(borrower, bookList);
    }

    public List<Book> getBooksBorrowedBy(FirstName name) {
        Borrower borrower = searchBorrower(name);
        return this.checkedOutBooks.get(borrower);
    }
}
