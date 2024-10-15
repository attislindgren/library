package se.thinkcode.library;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanServiceTest {
    private final LoanRepository loanRepository = new InMemoryLoanRepository();
    private final LoanService loanService = new LoanService(loanRepository);
    private final BookRepository bookRepository = new InMemoryBookRepository();
    private final BorrowerRepository borrowerRepository = new InMemoryBorrowerRepository();


    @Test
    void should_borrow_book() {
        Borrower borrower = getBorrower();
        Book book = getBook1();
        LocalDate today = LocalDate.of(2024, 10, 15);
        Loan expected = new Loan(book, today);

        loanService.borrowBook(book, borrower, today);
        Loan actual = loanService.getLoan(book, borrower);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_date_of_loan() {
        Borrower borrower = getBorrower();
        Book book = getBook1();
        LocalDate expected = LocalDate.of(2024, 10, 15);
        loanService.borrowBook(book, borrower, expected);

        LocalDate actual = loanService.getDateOfLoan(borrower, book);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_book() {
        Borrower borrower = getBorrower();
        Book book = getBook1();
        LocalDate loanDate = LocalDate.of(2024, 4, 1);
        LocalDate returnDate = LocalDate.of(2024, 10, 15);
        loanService.borrowBook(book, borrower, loanDate);

        loanService.returnBook(book, borrower, returnDate);
        LocalDate actual = loanService.getDateOfReturn(borrower, book);

        assertThat(actual).isEqualTo(returnDate);
    }

    @Test
    void should_return_list_of_loans_borrowed() {
        Borrower borrower = getBorrower();
        Book book = getBook1();
        LocalDate loanDate = LocalDate.of(2024, 4, 1);
        Loan loan1 = new Loan(book, loanDate);
        loanService.borrowBook(book, borrower, loanDate);
        book = getBook2();
        Loan loan2 = new Loan(book, loanDate);
        loanService.borrowBook(book, borrower, loanDate);

        List<Loan> actual = loanService.getLoans(borrower);

        assertThat(actual).containsExactly(loan1, loan2);
    }


    private Borrower getBorrower() {
        FirstName firstName = new FirstName("Kent");
        LastName lastName = new LastName("Sten");
        Email email = new Email("kent@sten.se");
        Borrower borrower = new Borrower(firstName, lastName, email);
        borrowerRepository.createBorrower(email, borrower);
        return borrower;
    }

    private Book getBook1() {
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
}
