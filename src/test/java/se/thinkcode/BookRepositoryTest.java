package se.thinkcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookRepositoryTest {

    @Test
    void should_delete_a_book() {
        BookRepository repository = new BookRepository();
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

}