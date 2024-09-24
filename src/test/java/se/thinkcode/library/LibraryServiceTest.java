package se.thinkcode.library;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LibraryServiceTest {
    private final BookRepository repository = new InMemoryBookRepository();
    private final BorrowerRepository borrowerRepository = new InMemoryBorrowerRepository();
    private final MailSenderStub mailSender = new MailSenderStub();
    private final LoanRepository loanRepository = new InMemoryLoanRepository();
    private final BorrowerService borrowerService = new BorrowerService(borrowerRepository);
    private final BookService bookService = new BookService(repository);

    private final LibraryService libraryService = new LibraryService(bookService, borrowerService, mailSender, loanRepository);

    @Test
    void should_find_a_book_called_extreme() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        List<Book> actual = libraryService.searchBooks(title);

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_a_book_called_1984() {
        ISBN isbn = new ISBN("9780470059029");
        Author author = new Author("George", "Orwell");
        Title title = new Title("1984");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        List<Book> actual = libraryService.searchBooks(title);

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_book_by_isbn() {
        ISBN isbn = new ISBN("9780470059029");
        Author author = new Author("George", "Orwell");
        Title title = new Title("1984");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        Book actual = libraryService.searchBooks(isbn);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_find_book_by_author() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        List<Book> actual = libraryService.searchByAuthor("Beck");

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_book_by_author_first_name() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        List<Book> actual = libraryService.searchByFirstName("Kent");

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_borrow_a_book() {
        Book book = getBook1();
        FirstName firstName = new FirstName("Kent");
        Borrower borrower = libraryService.searchBorrower(firstName);
        borrowBookToday(book, borrower);
        List<Book> actual = libraryService.getBooksBorrowedBy(firstName);

        assertThat(actual).contains(book);
    }

    @Test
    void should_borrow_two_books() {
        Book book1 = getBook1();
        Book book2 = getBook2();
        FirstName firstName = new FirstName("Kent");
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book1, borrower);
        libraryService.borrowBook(book2, borrower, today);

        List<Book> actual = libraryService.getBooksBorrowedBy(firstName);

        assertThat(actual).contains(book1, book2);
    }

    @Test
    void should_allow_two_persons_to_borrow_books() {
        Book book1 = getBook1();
        Book book2 = getBook2();
        FirstName kent = new FirstName("Kent");
        FirstName olle = new FirstName("Olle");
        Borrower borrower1 = libraryService.searchBorrower(kent);
        Borrower borrower2 = libraryService.searchBorrower(olle);
        LocalDate today = borrowBookToday(book1, borrower1);
        libraryService.borrowBook(book2, borrower2, today);

        List<Book> actual = libraryService.getBooksBorrowedBy(kent);
        assertThat(actual).containsExactly(book1);

        actual = libraryService.getBooksBorrowedBy(olle);
        assertThat(actual).containsExactly(book2);
    }

    @Test
    void should_return_date_of_loan() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);

        LocalDate actual = libraryService.getDateOfLoan(borrower, book);

        assertThat(actual).isEqualTo(today);
    }


    @Test
    void should_return_a_borrowed_book() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);
        LocalDate returnDate = returnBook(today, 25, book, borrower);
        LocalDate actual = libraryService.getDateOfReturn(borrower, book);

        assertThat(actual).isEqualTo(returnDate);
    }

    @Test
    void should_return_fine_for_overdue_book() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);
        returnBook(today, 35, book, borrower);

        int actual = libraryService.getFine(book, borrower);

        assertThat(actual).isEqualTo(50);
    }

    @Test
    void book_returned_on_time_should_not_be_fined() {
        Book book = getBook1();
        Borrower borrower = getBorrower();
        LocalDate today = borrowBookToday(book, borrower);
        returnBook(today, 15, book, borrower);

        int actual = libraryService.getFine(book, borrower);

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

        double actual = libraryService.averageLoanTime(borrower);
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
        borrower = libraryService.searchBorrower(olle);
        today = borrowBookToday(book, borrower);
        returnBook(today, 35, book, borrower);

        List<Borrower> expected = new ArrayList<>();
        expected.add(borrower);

        libraryService.sendLateMail();
        List<Borrower> actual = mailSender.hasSentMail();

        assertThat(actual).isEqualTo(expected);
    }

    private LocalDate returnBook(LocalDate today, int daysToAdd, Book book, Borrower borrower) {
        LocalDate returnDate = today.plusDays(daysToAdd);
        libraryService.returnBook(book, borrower, returnDate);
        return returnDate;
    }

    private LocalDate borrowBookToday(Book book, Borrower borrower) {
        LocalDate today = LocalDate.of(2024, 4, 16);
        libraryService.borrowBook(book, borrower, today);
        return today;
    }

    private Borrower getBorrower() {
        FirstName firstName = new FirstName("Kent");
        return libraryService.searchBorrower(firstName);
    }

    private Book getBook1() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book book = new Book(title, isbn, author);
        repository.create(book);
        return book;
    }

    private Book getBook2() {
        ISBN isbn = new ISBN("9780470059029");
        Author author = new Author("George", "Orwell");
        Title title = new Title("1984");
        Book book = new Book(title, isbn, author);
        repository.create(book);
        return book;
    }
}
