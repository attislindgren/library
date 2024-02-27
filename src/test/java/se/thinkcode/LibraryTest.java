package se.thinkcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LibraryTest {
    @Test
    void should_find_a_book_called_extreme() {
        Library library = new Library();
        Book expected = new Book("Extreme");

        List<Book> actual = library.searchBooks("Extreme");

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_a_book_called_1984() {
        Library library = new Library();
        Book expected = new Book("1984");

        List<Book> actual = library.searchBooks("1984");

        assertThat(actual).containsExactly(expected);
    }
}
