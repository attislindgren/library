package se.thinkcode;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanRepositoryTest {
    LoanRepository loanRepository = new InMemoryLoanRepository();
    BookRepository bookRepository = new InMemoryBookRepository();
    BorrowerRepository borrowerRepository = new InMemoryBorrowerRepository();

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

    private Book getBook() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book book = new Book(title, isbn, author);
        bookRepository.create(book);
        return book;
    }

    private Borrower getBorrower() {
        FirstName firstName = new FirstName("Kent");
        return borrowerRepository.searchBorrower(firstName);
    }
}
