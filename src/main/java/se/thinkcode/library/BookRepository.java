package se.thinkcode.library;

import java.util.List;

public interface BookRepository {
    void create(Book book);

    List<Book> searchBooks(Title title);

    Book searchBooks(ISBN isbn);

    List<Book> searchByAuthor(String surname);

    List<Book> searchByFirstName(String firstName);

    void delete(ISBN isbn);

    void delete();
}
