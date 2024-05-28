package se.thinkcode;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    public static final int loanDayLimit = 30;
    public static final int finePerDay = 10;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final MailSender mailSender;
    private final Map<Borrower, List<Loan>> checkedOutLoans = new HashMap<>();


    public Library(BookRepository bookRepository,
                   BorrowerRepository borrowerRepository,
                   MailSender mailSender) {

        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
        this.mailSender = mailSender;
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
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        for (Loan currentLoan : loanList) {
            if (currentLoan.getBook().equals(book)) {
                return currentLoan;
            }
        }
        return null;
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
        List<Loan> loanList = this.checkedOutLoans.get(borrower);
        int length = 0;
        for (Loan currentLoan : loanList) {
            length = length + daysLoaned(currentLoan);
        }
        return (double) length / loanList.size();
    }

    public void sendLateMail() {
        Map<Borrower, List<Loan>> loans = this.checkedOutLoans;
        List<Borrower> borrowers = new ArrayList<>(loans.keySet());
        List<Loan> loanList;
        Map<Borrower, List<Loan>> lateLoans = new HashMap<>();
        List<Loan> lateLoan = new ArrayList<>();
        List<Borrower> lateBorrowers = new ArrayList<>();
        for (Borrower currentBorrower : borrowers) {
            loanList = this.checkedOutLoans.get(currentBorrower);
            for (Loan loan : loanList) {
                if (getDaysLate(loan) != 0) {
                    lateLoan.add(loan);
                    lateBorrowers.add(currentBorrower);
                }
                lateLoans.put(currentBorrower, lateLoan);
            }
        }

        mailSender.sendEmail(lateBorrowers);

    }
}
