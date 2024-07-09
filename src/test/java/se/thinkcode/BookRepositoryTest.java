package se.thinkcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BookRepositoryTest {
    BookRepository repository;
    @Test
    void should_delete_a_book() {
        ISBN isbn = new ISBN("9789171195739");
        Author author = new Author("Michael", "Crichton");
        Title title = new Title("Jurassic Park");
        Book book = new Book(title, isbn, author);
        repository.create(book);

        Book actual = repository.searchBooks(isbn);

        assertThat(actual).isEqualTo(book);
        repository.delete(isbn);

        actual = repository.searchBooks(isbn);

        assertThat(actual).isNull();
    }

    @Test
    void should_find_a_book_called_1984() {
        ISBN isbn = new ISBN("9780470059029");
        Author author = new Author("George", "Orwell");
        Title title = new Title("1984");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        List<Book> actual = repository.searchBooks(title);

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_book_by_author_first_name() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        List<Book> actual = repository.searchByFirstName("Kent");

        assertThat(actual).containsExactly(expected);
    }

    @Test
    void should_find_book_by_isbn() {
        ISBN isbn = new ISBN("9780470059029");
        Author author = new Author("George", "Orwell");
        Title title = new Title("1984");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        Book actual = repository.searchBooks(isbn);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_find_book_by_author() {
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book expected = new Book(title, isbn, author);
        repository.create(expected);

        List<Book> actual = repository.searchByAuthor("Beck");

        assertThat(actual).containsExactly(expected);
    }
}