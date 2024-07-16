package se.thinkcode;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository {
    void borrowBook(Book book, Borrower borrower, LocalDate date);

    List<Book> getBooksBorrowedBy(Borrower borrower);

    LocalDate getDateOfLoan(Borrower borrower, Book book);

    void returnBook(Book book, Borrower borrower, LocalDate returnDate);

    LocalDate getDateOfReturn(Borrower borrower, Book book);

    Loan getLoan(Book book, Borrower borrower);

    List<Loan> getLoans(Borrower borrower);

    List<Borrower> getBorrowers();
}
