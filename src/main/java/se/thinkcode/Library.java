package se.thinkcode;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Library {
    public static final int loanDayLimit = 30;
    public static final int finePerDay = 10;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final MailSender mailSender;
    private final LoanRepository loanRepository;


    public Library(BookRepository bookRepository,
                   BorrowerRepository borrowerRepository,
                   MailSender mailSender,
                   LoanRepository loanRepository) {

        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
        this.mailSender = mailSender;
        this.loanRepository = loanRepository;
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
        loanRepository.borrowBook(book, borrower, date);
    }

    public List<Book> getBooksBorrowedBy(FirstName name) {
        Borrower borrower = searchBorrower(name);
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
        return loanRepository.getLoan(book, borrower);

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
        List<Loan> loanList = loanRepository.getLoans(borrower);
        int length = 0;
        for (Loan currentLoan : loanList) {
            length = length + daysLoaned(currentLoan);
        }
        return (double) length / loanList.size();
    }

    public void sendLateMail() {
        List<Borrower> borrowers = loanRepository.getBorrowers();
        List<Loan> loanList;
        List<Borrower> lateBorrowers = new ArrayList<>();
        for (Borrower currentBorrower : borrowers) {
            loanList = loanRepository.getLoans(currentBorrower);
            for (Loan loan : loanList) {
                if (getDaysLate(loan) != 0) {
                    lateBorrowers.add(currentBorrower);
                }
            }
        }

        mailSender.sendEmail(lateBorrowers);

    }
}
