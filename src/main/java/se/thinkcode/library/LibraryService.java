package se.thinkcode.library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    public static final int loanDayLimit = 30;
    public static final int finePerDay = 10;
    private final BookService bookService;
    private final BorrowerService borrowerService;
    private final MailSender mailSender;
    private final LoanService loanService;


    public LibraryService(BookService bookService,
                          BorrowerService borrowerService,
                          MailSender mailSender,
                          LoanService loanService) {

        this.bookService = bookService;
        this.borrowerService = borrowerService;
        this.mailSender = mailSender;
        this.loanService = loanService;
    }

    public List<Book> searchBooks(Title title) {
        return bookService.searchBooks(title);
    }

    public Book searchBooks(ISBN isbn) {
        return bookService.searchBooks(isbn);
    }

    public List<Book> searchByAuthor(String surname) {
        return bookService.searchByAuthor(surname);
    }

    public List<Book> searchByFirstName(String firstName) {
        return bookService.searchByFirstName(firstName);
    }

    public Borrower searchBorrower(FirstName name) {
        return borrowerService.searchBorrower(name);
    }

    public void borrowBook(Book book, Borrower borrower, LocalDate date) {
        loanService.borrowBook(book, borrower, date);
    }

    public List<Book> getBooksBorrowedBy(FirstName name) {
        Borrower borrower = searchBorrower(name);
        return loanService.getBooksBorrowedBy(borrower);
    }

    public LocalDate getDateOfLoan(Borrower borrower, Book book) {
        return loanService.getDateOfLoan(borrower, book);
    }

    public void returnBook(Book book, Borrower borrower, LocalDate returnDate) {
        loanService.returnBook(book, borrower, returnDate);
    }

    public LocalDate getDateOfReturn(Borrower borrower, Book book) {
        return loanService.getDateOfReturn(borrower, book);
    }

    public int getFine(Book book, Borrower borrower) {
        Loan currentLoan = getLoan(book, borrower);
        int daysLate = getDaysLate(currentLoan);
        return calculateFine(daysLate);
    }

    private static int calculateFine(int daysLate) {
        return daysLate * finePerDay;
    }

    private static int getDaysLate(Loan loan) {
        int daysLate = 0;
        if (loan != null) {
            int daysBorrowed = daysLoaned(loan);
            if (daysBorrowed > loanDayLimit) {
                daysLate = daysBorrowed - loanDayLimit;
            }
        }
        return daysLate;
    }

    private Loan getLoan(Book book, Borrower borrower) {
        return loanService.getLoan(book, borrower);

    }

    private static int daysLoaned(Loan loan) {
        if (loan != null) {
            LocalDate dateBorrowed = loan.getDateBorrowed();
            LocalDate returnDate = loan.getReturnDate();
            return (int) dateBorrowed.until(returnDate, ChronoUnit.DAYS);
        }
        return 0;
    }

    public double averageLoanTime(Borrower borrower) {
        List<Loan> loanList = loanService.getLoans(borrower);
        int length = 0;
        for (Loan currentLoan : loanList) {
            length = length + daysLoaned(currentLoan);
        }
        return (double) length / loanList.size();
    }

    public void sendLateMail() {
        List<Borrower> borrowers = loanService.getBorrowers();
        List<Loan> loanList;
        List<Borrower> lateBorrowers = new ArrayList<>();
        for (Borrower currentBorrower : borrowers) {
            loanList = loanService.getLoans(currentBorrower);
            for (Loan loan : loanList) {
                if (getDaysLate(loan) != 0) {
                    lateBorrowers.add(currentBorrower);
                }
            }
        }

        mailSender.sendEmail(lateBorrowers);

    }

}
