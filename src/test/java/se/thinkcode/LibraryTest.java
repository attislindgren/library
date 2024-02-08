package se.thinkcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LibraryTest {
    @Test
    void should_return_list_of_titles() {
        Library library = new Library();

        List<Book> actual = library.searchBooks("Extreme");

        assertThat(actual).isNotEmpty();
    }
}
