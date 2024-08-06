package se.thinkcode.library;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class LoanRepositoryTest {
    LoanRepository loanRepository;
    BookRepository bookRepository;

    @Test
    void should_return_a_borrowed_book() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        LocalDate today = LocalDate.of(2024, 7, 16);
        loanRepository.borrowBook(book, borrower, today);
        LocalDate returnDate = today.plusDays(25);
        loanRepository.returnBook(book, borrower, returnDate);

        LocalDate actual = loanRepository.getDateOfReturn(borrower, book);

        assertThat(actual).isEqualTo(returnDate);
    }

    @Test
    void should_get_books_borrowed_by() {
        Borrower borrower = getBorrower();
        Book book = getBook();
        LocalDate today = LocalDate.of(2024, 7, 16);
        loanRepository.borrowBook(book, borrower, today);

        List<Book> actual = loanRepository.getBooksBorrowedBy(borrower);

        assertThat(actual).contains(book);
    }

    @Test
    void should_return_date_of_return() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        LocalDate today = LocalDate.of(2024, 7, 16);
        loanRepository.borrowBook(book, borrower, today);

        LocalDate actual = loanRepository.getDateOfLoan(borrower, book);

        assertThat(actual).isEqualTo(today);

    }

    @Test
    void should_get_loan() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        LocalDate today = LocalDate.of(2024, 7, 16);
        loanRepository.borrowBook(book, borrower, today);

        Loan actual = loanRepository.getLoan(book, borrower);
        Loan expected = new Loan(book, today);


        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_get_two_loans() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        LocalDate today = LocalDate.of(2024, 7, 16);
        loanRepository.borrowBook(book, borrower, today);
        Loan loan1 = new Loan(book, today);
        Book book2 = getBook2();
        loanRepository.borrowBook(book2, borrower, today);
        Loan loan2 = new Loan(book2, today);

        List<Loan> actual = loanRepository.getLoans(borrower);

        assertThat(actual).contains(loan1, loan2);
    }

    @Test
    void should_return_list_of_borrowers() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        Borrower borrower2 = getBorrower2();
        LocalDate today = LocalDate.of(2024, 7, 16);
        loanRepository.borrowBook(book, borrower, today);
        Book book2 = getBook2();
        loanRepository.borrowBook(book2, borrower2, today);

        List<Borrower> actual = loanRepository.getBorrowers();

        assertThat(actual).contains(borrower, borrower2);
    }

    private Book getBook() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book book = new Book(title, isbn, author);
        bookRepository.create(book);
        return book;
    }

    private Book getBook2() {
        ISBN isbn = new ISBN("9780470059029");
        Author author = new Author("George", "Orwell");
        Title title = new Title("1984");
        Book book = new Book(title, isbn, author);
        bookRepository.create(book);
        return book;
    }

    private Borrower getBorrower() {
        FirstName firstName = new FirstName("Kent");
        return new Borrower(firstName);
    }

    private Borrower getBorrower2() {
        FirstName firstName = new FirstName("Olle");
        return new Borrower(firstName);
    }
}
