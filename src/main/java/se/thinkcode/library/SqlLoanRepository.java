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
        String firstName = borrower.getName().getFirstName();
        dao.borrowBook(isbn, firstName, date);
    }

    @Override
    public List<Book> getBooksBorrowedBy(Borrower borrower) {
        LoanDao dao = databaseConnection.getLoanDao();
        String firstName = borrower.getName().getFirstName();
        return dao.getBooksBorrowedBy(firstName);
    }

    @Override
    public LocalDate getDateOfLoan(Borrower borrower, Book book) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String firstName = borrower.getName().getFirstName();
        return dao.getDateOfLoan(isbn, firstName);
    }

    @Override
    public void returnBook(Book book, Borrower borrower, LocalDate returnDate) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String firstName = borrower.getName().getFirstName();
        dao.returnBook(isbn, firstName, returnDate);
    }

    @Override
    public LocalDate getDateOfReturn(Borrower borrower, Book book) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String firstName = borrower.getName().getFirstName();
        return dao.getDateOfReturn(isbn, firstName);
    }

    @Override
    public Loan getLoan(Book book, Borrower borrower) {
        LoanDao dao = databaseConnection.getLoanDao();
        String isbn = book.isbn().getIsbn();
        String firstName = borrower.getName().getFirstName();
        return dao.getLoan(isbn, firstName);
    }

    @Override
    public List<Loan> getLoans(Borrower borrower) {
        LoanDao dao = databaseConnection.getLoanDao();
        String firstName = borrower.getName().getFirstName();
        return dao.getLoans(firstName);
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
