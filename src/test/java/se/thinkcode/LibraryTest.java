package se.thinkcode;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LibraryTest {
    private final BookRepository repository = new InMemoryBookRepository();
    private final BorrowerRepository borrowerRepository = new BorrowerRepository();
    private final MailSenderStub mailSender = new MailSenderStub();
    private final Library library = new Library(repository, borrowerRepository, mailSender);

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
        Book book = getBook1();
        FirstName firstName = new FirstName("Kent");
        Borrower borrower = library.searchBorrower(firstName);
        borrowBookToday(book, borrower);
        List<Book> actual = library.getBooksBorrowedBy(firstName);

        assertThat(actual).contains(book);
    }

    @Test
    void should_borrow_two_books() {
        Book book1 = getBook1();
        Book book2 = getBook2();
        FirstName firstName = new FirstName("Kent");
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book1, borrower);
        library.borrowBook(book2, borrower, today);

        List<Book> actual = library.getBooksBorrowedBy(firstName);

        assertThat(actual).contains(book1, book2);
    }

    @Test
    void should_allow_two_persons_to_borrow_books() {
        Book book1 = getBook1();
        Book book2 = getBook2();
        FirstName kent = new FirstName("Kent");
        FirstName olle = new FirstName("Olle");
        Borrower borrower1 = library.searchBorrower(kent);
        Borrower borrower2 = library.searchBorrower(olle);
        LocalDate today = borrowBookToday(book1, borrower1);
        library.borrowBook(book2, borrower2, today);

        List<Book> actual = library.getBooksBorrowedBy(kent);
        assertThat(actual).containsExactly(book1);

        actual = library.getBooksBorrowedBy(olle);
        assertThat(actual).containsExactly(book2);
    }

    @Test
    void should_return_date_of_loan() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);

        LocalDate actual = library.getDateOfLoan(borrower, book);

        assertThat(actual).isEqualTo(today);
    }


    @Test
    void should_return_a_borrowed_book() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);
        LocalDate returnDate = returnBook(today, 25, book, borrower);
        LocalDate actual = library.getDateOfReturn(borrower, book);

        assertThat(actual).isEqualTo(returnDate);
    }

    @Test
    void should_return_fine_for_overdue_book() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);
        returnBook(today, 35, book, borrower);

        int actual = library.getFine(book, borrower);

        assertThat(actual).isEqualTo(50);
    }

    @Test
    void book_returned_on_time_should_not_be_fined() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);
        returnBook(today, 15, book, borrower);

        int actual = library.getFine(book, borrower);

        assertThat(actual).isEqualTo(0);
    }

    @Test
    void should_calculate_average_loan_time() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);
        returnBook(today, 15, book, borrower);

        book = getBook2();
        today = borrowBookToday(book, borrower);
        returnBook(today, 19, book, borrower);

        double actual = library.averageLoanTime(borrower);
        assertThat(actual).isEqualTo(17.0);
    }

    @Test
    void should_not_send_mail_for_empty_list_of_loans() {
        List<Borrower> actual = mailSender.hasSentMail();
        assertThat(actual).isEmpty();
    }

    @Test
    void should_send_email_for_late_return() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);
        returnBook(today, 15, book, borrower);

        book = getBook2();
        FirstName olle = new FirstName("Olle");
        borrower = library.searchBorrower(olle);
        today = borrowBookToday(book, borrower);
        returnBook(today, 35, book, borrower);

        List<Borrower> expected = new ArrayList();
        expected.add(borrower);

        library.sendLateMail();
        List<Borrower> actual = mailSender.hasSentMail();

        assertThat(actual).isEqualTo(expected);
    }

    private LocalDate returnBook(LocalDate today, int daysToAdd, Book book, Borrower borrower) {
        LocalDate returnDate = today.plusDays(daysToAdd);
        library.returnBook(book, borrower, returnDate);
        return returnDate;
    }

    private LocalDate borrowBookToday(Book book, Borrower borrower) {
        LocalDate today = LocalDate.of(2024, 4, 16);
        library.borrowBook(book, borrower, today);
        return today;
    }

    private Borrower getBorrower() {
        FirstName firstName = new FirstName("Kent");
        return library.searchBorrower(firstName);
    }

    private Book getBook1() {
        ISBN isbn = new ISBN("9780596809485");
        return library.searchBooks(isbn);
    }

    private Book getBook2() {
        ISBN isbn = new ISBN("9780470059029");
        return library.searchBooks(isbn);
    }
}
