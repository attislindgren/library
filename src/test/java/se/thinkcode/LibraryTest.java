package se.thinkcode;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LibraryTest {
    private final BookRepository repository = new BookRepository();
    private final BorrowerRepository borrowerRepository = new BorrowerRepository();
    private final Library library = new Library(repository, borrowerRepository);

    @Test
    void should_find_a_book_called_extreme() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);

        List<Book> actual = library.searchBooks(title);

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_a_book_called_1984() {
        ISBN isbn = new ISBN("9780470059029");
        Author author = new Author("George", "Orwell");
        Title title = new Title("1984");
        Book expected = new Book(title, isbn, author);

        List<Book> actual = library.searchBooks(title);

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_book_by_isbn() {
        ISBN isbn = new ISBN("9780470059029");
        Author author = new Author("George", "Orwell");
        Title title = new Title("1984");
        Book expected = new Book(title, isbn, author);


        Book actual = library.searchBooks(isbn);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_find_book_by_author() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);

        List<Book> actual = library.searchByAuthor("Beck");

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_book_by_author_first_name() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);

        List<Book> actual = library.searchByFirstName("Kent");

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_borrow_a_book() {
        Book book = getBook();
        FirstName firstName = new FirstName("Kent");
        Borrower borrower = library.searchBorrower(firstName);
        LocalDate today = LocalDate.of(2024, 4, 16);

        library.borrowBook(book, borrower, today);
        List<Book> actual = library.getBooksBorrowedBy(firstName);

        assertThat(actual).contains(book);
    }

    @Test
    void should_borrow_two_books() {
        ISBN isbn1 = new ISBN("9780596809485");
        ISBN isbn2 = new ISBN("9780470059029");
        Book book1 = library.searchBooks(isbn1);
        Book book2 = library.searchBooks(isbn2);
        FirstName firstName = new FirstName("Kent");
        Borrower borrower = library.searchBorrower(firstName);
        LocalDate today = LocalDate.of(2024, 4, 16);

        library.borrowBook(book1, borrower, today);
        library.borrowBook(book2, borrower, today);

        List<Book> actual = library.getBooksBorrowedBy(firstName);

        assertThat(actual).contains(book1, book2);
    }

    @Test
    void should_allow_two_persons_to_borrow_books() {
        ISBN isbn1 = new ISBN("9780596809485");
        ISBN isbn2 = new ISBN("9780470059029");
        Book book1 = library.searchBooks(isbn1);
        Book book2 = library.searchBooks(isbn2);
        FirstName kent = new FirstName("Kent");
        FirstName olle = new FirstName("Olle");
        Borrower borrower1 = library.searchBorrower(kent);
        Borrower borrower2 = library.searchBorrower(olle);
        LocalDate today = LocalDate.of(2024, 4, 16);

        library.borrowBook(book1, borrower1, today);
        library.borrowBook(book2, borrower2, today);

        List<Book> actual = library.getBooksBorrowedBy(kent);
        assertThat(actual).containsExactly(book1);

        actual = library.getBooksBorrowedBy(olle);
        assertThat(actual).containsExactly(book2);
    }

    @Test
    void should_return_date_of_loan() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        LocalDate today = LocalDate.of(2024, 4, 16);


        library.borrowBook(book, borrower, today);


        LocalDate actual = library.getDateOfLoan(borrower, book);

        assertThat(actual).isEqualTo(today);
    }


    @Test
    void should_return_a_borrowed_book() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        LocalDate today = LocalDate.of(2024, 4, 16);
        library.borrowBook(book, borrower, today);
        LocalDate returnDate = today.plusDays(25);

        library.returnBook(book, borrower, returnDate);
        LocalDate actual = library.getDateOfReturn(borrower, book);

        assertThat(actual).isEqualTo(returnDate);
    }

    @Test
    void should_return_fine_for_overdue_book() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        LocalDate today = LocalDate.of(2024, 4, 16);
        library.borrowBook(book, borrower, today);
        LocalDate returnDate = today.plusDays(35);
        library.returnBook(book, borrower, returnDate);

        int actual = library.getFine(book, borrower);

        assertThat(actual).isEqualTo(50);
    }

    @Test
    void book_returned_on_time_should_not_be_fined() {
        Book book = getBook();
        Borrower borrower = getBorrower();
        LocalDate today = LocalDate.of(2024, 4, 16);
        library.borrowBook(book, borrower, today);
        LocalDate returnDate = today.plusDays(15);
        library.returnBook(book, borrower, returnDate);

        int actual = library.getFine(book, borrower);

        assertThat(actual).isEqualTo(0);
    }

    private Borrower getBorrower() {
        FirstName firstName = new FirstName("Kent");
        return library.searchBorrower(firstName);
    }

    private Book getBook() {
        ISBN isbn = new ISBN("9780596809485");
        return library.searchBooks(isbn);
    }
}
