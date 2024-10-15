package se.thinkcode.library;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest {
    private final BookRepository bookRepository = new InMemoryBookRepository();
    private final BookService bookService = new BookService(bookRepository);

    @Test
    void should_create_and_find_book_by_isbn() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);
        bookService.createBook(expected);

        Book actual = bookService.searchBooks(isbn);

        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void should_find_book_by_title() {
        ISBN isbn = new ISBN("9781785030253");
        Author author = new Author("Andy", "Weir");
        Title title = new Title("Artemis");
        Book expected = new Book(title, isbn, author);
        bookService.createBook(expected);

        List<Book> actual = bookService.searchBooks(title);

        assertThat(actual).contains(expected);
    }

    @Test
    void should_return_list_of_books_by_author() {
        ISBN isbn = new ISBN("9781785030253");
        Author author = new Author("Andy", "Weir");
        Title title = new Title("Artemis");
        Book bookA = new Book(title, isbn, author);
        bookService.createBook(bookA);

        isbn = new ISBN("9780553418026");
        author = new Author("Andy", "Weir");
        title = new Title("The Martian");
        Book bookB = new Book(title, isbn, author);
        bookService.createBook(bookB);

        isbn = new ISBN("9781526610140");
        author = new Author("Madeline", "Miller");
        title = new Title("Circe");
        Book bookC = new Book(title, isbn, author);
        bookService.createBook(bookC);

        List<Book> actual = bookService.searchByAuthor("Weir");

        assertThat(actual).contains(bookA, bookB);
        assertThat(bookC).isNotIn(actual);
    }

    @Test
    void should_return_list_of_books_by_author_firstname() {
        ISBN isbn = new ISBN("9781785030253");
        Author author = new Author("Andy", "Weir");
        Title title = new Title("Artemis");
        Book bookA = new Book(title, isbn, author);
        bookService.createBook(bookA);

        isbn = new ISBN("9780553418026");
        author = new Author("Andy", "Weir");
        title = new Title("The Martian");
        Book bookB = new Book(title, isbn, author);
        bookService.createBook(bookB);

        isbn = new ISBN("9781526610140");
        author = new Author("Madeline", "Miller");
        title = new Title("Circe");
        Book bookC = new Book(title, isbn, author);
        bookService.createBook(bookC);

        List<Book> actual = bookService.searchByFirstName("Andy");

        assertThat(actual).contains(bookA, bookB);
        assertThat(bookC).isNotIn(actual);
    }
}
