package se.thinkcode.library;

import java.time.LocalDate;
import java.util.List;

public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void borrowBook(Book book, Borrower borrower, LocalDate date) {
        loanRepository.borrowBook(book, borrower, date);
    }

    public List<Book> getBooksBorrowedBy(Borrower borrower) {
        return loanRepository.getBooksBorrowedBy(borrower);
    }

    public LocalDate getDateOfLoan(Borrower borrower, Book book) {
        return loanRepository.getDateOfLoan(borrower, book);
    }

    public void returnBook(Book book, Borrower borrower, LocalDate returnDate) {
        loanRepository.returnBook(book, borrower, returnDate);
    }

    public LocalDate getDateOfReturn(Borrower borrower, Book book) {
        return loanRepository.getDateOfReturn(borrower, book);
    }

    public Loan getLoan(Book book, Borrower borrower) {
        return loanRepository.getLoan(book, borrower);
    }

    public List<Loan> getLoans(Borrower borrower) {
        return loanRepository.getLoans(borrower);
    }

    public List<Borrower> getBorrowers() {
        return loanRepository.getBorrowers();
    }

}
