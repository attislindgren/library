package se.thinkcode;

import java.util.*;

public class Library {
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final Map<Borrower, List<Loan>> checkedOutLoans = new HashMap<>();


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

    public void borrowBook(Book book, Borrower borrower, Date date) {
        List<Loan> loanList = this.checkedOutLoans.getOrDefault(borrower, new ArrayList<>());
        Loan loan = new Loan(book, date);
        loanList.add(loan);
        this.checkedOutLoans.put(borrower, loanList);
    }

    public List<Book> getBooksBorrowedBy(FirstName name) {
        List<Book> books = new ArrayList<>();
        Borrower borrower = searchBorrower(name);
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            Book currentBook = currentLoan.getBook();
            books.add(currentBook);
        }
        return books;
    }

    public Date getDateOfLoan(Borrower borrower, Book book) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                return currentLoan.getTime();
            }
        }
        return null;
    }
}
