package se.thinkcode.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLoanRepository implements LoanRepository {
    private final Map<Borrower, List<Loan>> checkedOutLoans = new HashMap<>();

    @Override
    public void borrowBook(Book book, Borrower borrower, LocalDate date) {
        List<Loan> loanList = this.checkedOutLoans.getOrDefault(borrower, new ArrayList<>());
        Loan loan = new Loan(book, date);
        loanList.add(loan);
        this.checkedOutLoans.put(borrower, loanList);
    }

    @Override
    public List<Book> getBooksBorrowedBy(Borrower borrower) {
        List<Book> books = new ArrayList<>();
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            Book currentBook = currentLoan.getBook();
            books.add(currentBook);
        }
        return books;
    }

    @Override
    public LocalDate getDateOfLoan(Borrower borrower, Book book) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                return currentLoan.getDateBorrowed();
            }
        }
        return null;
    }

    @Override
    public void returnBook(Book book, Borrower borrower, LocalDate returnDate) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                currentLoan.setReturnDate(returnDate);
            }
        }
    }

    @Override
    public LocalDate getDateOfReturn(Borrower borrower, Book book) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                return currentLoan.getReturnDate();
            }
        }
        return null;
    }

    @Override
    public Loan getLoan(Book book, Borrower borrower) {
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                return currentLoan;
            }
        }
        return null;
    }

    @Override
    public List<Loan> getLoans(Borrower borrower) {
        return this.checkedOutLoans.get(borrower);
    }

    @Override
    public List<Borrower> getBorrowers() {
        return new ArrayList<>(this.checkedOutLoans.keySet());
    }

    @Override
    public void delete() {

    }
}
