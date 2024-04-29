package se.thinkcode;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void borrowBook(Book book, Borrower borrower, LocalDate date) {
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

    public LocalDate getDateOfLoan(Borrower borrower, Book book) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                return currentLoan.getDateBorrowed();
            }
        }
        return null;
    }

    public void returnBook(Book book, Borrower borrower, LocalDate returnDate) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                currentLoan.setReturnDate(returnDate);
            }
        }
    }

    public LocalDate getDateOfReturn(Borrower borrower, Book book) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                return currentLoan.getReturnDate();
            }
        }
        return null;
    }

    public int getFine(Book book, Borrower borrower) {
        Loan currentLoan = getLoan(book, borrower);
        int daysLate = getDaysLate(currentLoan);
        return calculateFine(daysLate);
    }

    private static int calculateFine(int daysLate) {
        return daysLate * 10;
    }

    private static int getDaysLate(Loan loan) {
        int daysLate = 0;
        if (loan != null) {
            LocalDate dateBorrowed = loan.getDateBorrowed();
            LocalDate returnDate = loan.getReturnDate();
            int daysBorrowed = (int) dateBorrowed.until(returnDate, ChronoUnit.DAYS);
            if (daysBorrowed > 30) {
                daysLate = daysBorrowed - 30;
            }
        }
        return daysLate;
    }

    private Loan getLoan(Book book, Borrower borrower) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                return currentLoan;
            }
        }
        return null;
    }
}
