package se.thinkcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {
    private final Map<ISBN, Book> books;

    public BookRepository() {
        books = new HashMap<>();

        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book book = new Book(title, isbn, author);
        create(book);

        author = new Author("George", "Orwell");
        isbn = new ISBN("9780470059029");
        title = new Title("1984");
        book = new Book(title, isbn, author);
        create(book);
    }

    public void create(Book book) {
        ISBN isbn = book.isbn();
        books.put(isbn, book);
    }

    public List<Book> searchBooks(Title title) {
        List<Book> booksWithTitle = new ArrayList<>();
        for (Book currentBook : books.values()) {
            if (currentBook.title().equals(title)) {
                booksWithTitle.add(currentBook);
            }
        }
        return booksWithTitle;
    }

    public Book searchBooks(ISBN isbn) {
        return books.get(isbn);
    }

    public List<Book> searchByAuthor(String surname) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book currentBook : books.values()) {
            if (currentBook.author().getSurname().equals(surname)) {
                booksByAuthor.add(currentBook);
            }
        }
        return booksByAuthor;
    }

    public List<Book> searchByFirstName(String firstName) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book currentBook : books.values()) {
            if (currentBook.author().getFirstName().equals(firstName)) {
                booksByAuthor.add(currentBook);
            }
        }
        return booksByAuthor;
    }

    public void delete(ISBN isbn) {
        books.remove(isbn);
    }
}
