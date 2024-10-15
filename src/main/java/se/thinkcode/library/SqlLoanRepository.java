package se.thinkcode.library;

import se.thinkcode.infrastructure.DatabaseConnection;

import java.time.LocalDate;
import java.util.List;

public class SqlLoanRepository implements LoanRepository {
    private final DatabaseConnection databaseConnection;

    public SqlLoanRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void borrowBook(Book book, Borrower borrower, LocalDate date) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String email = borrower.getEmail().email();
        dao.borrowBook(isbn, email, date);
    }

    @Override
    public List<Book> getBooksBorrowedBy(Borrower borrower) {
        LoanDao dao = databaseConnection.getLoanDao();
        String email = borrower.getEmail().email();
        return dao.getBooksBorrowedBy(email);
    }

    @Override
    public LocalDate getDateOfLoan(Borrower borrower, Book book) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String email = borrower.getEmail().email();
        return dao.getDateOfLoan(isbn, email);
    }

    @Override
    public void returnBook(Book book, Borrower borrower, LocalDate returnDate) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String email = borrower.getEmail().email();
        dao.returnBook(isbn, email, returnDate);
    }

    @Override
    public LocalDate getDateOfReturn(Borrower borrower, Book book) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String email = borrower.getEmail().email();
        return dao.getDateOfReturn(isbn, email);
    }

    @Override
    public Loan getLoan(Book book, Borrower borrower) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String email = borrower.getEmail().email();
        return dao.getLoan(isbn, email);
    }

    @Override
    public List<Loan> getLoans(Borrower borrower) {
        LoanDao dao = databaseConnection.getLoanDao();
        String email = borrower.getEmail().email();
        return dao.getLoans(email);
    }

    @Override
    public List<Borrower> getBorrowers() {
        LoanDao dao = databaseConnection.getLoanDao();
        return dao.getBorrowers();
    }

    @Override
    public void delete() {
        LoanDao dao = databaseConnection.getLoanDao();
        dao.delete();
    }
}
