package se.thinkcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LibraryTest {
    @Test
    void should_find_a_book_called_extreme() {
        Library library = new Library();
        ISBN isbn = new ISBN("9780596809485");
        Book expected = new Book("Extreme", isbn);

        List<Book> actual = library.searchBooks("Extreme");

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_a_book_called_1984() {
        Library library = new Library();
        ISBN isbn = new ISBN("9780470059029");
        Book expected = new Book("1984", isbn);

        List<Book> actual = library.searchBooks("1984");

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_book_by_isbn() {
        Library library = new Library();
        ISBN isbn = new ISBN("9780470059029");
        Book expected = new Book("1984", isbn);


        List<Book> actual = library.searchBooks(isbn);

        assertThat(actual).containsExactly(expected);
    }
}
