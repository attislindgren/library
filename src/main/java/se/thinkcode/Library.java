package se.thinkcode;

import java.util.List;

public class Library {
    private final Repository repository = new Repository();

    public Library() {

    }

    public List<Book> searchBooks(Title title) {
        return repository.searchBooks(title);
    }

    public Book searchBooks(ISBN isbn) {
        return repository.searchBooks(isbn);
    }

    public List<Book> searchByAuthor(String surname) {
        return repository.searchByAuthor(surname);
    }

    public List<Book> searchByFirstName(String firstName) {
        return repository.searchByFirstName(firstName);
    }
}
