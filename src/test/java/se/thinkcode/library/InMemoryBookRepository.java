package se.thinkcode.library;

import se.thinkcode.util.MyList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBookRepository implements BookRepository {
    private final Map<ISBN, Book> books;

    public InMemoryBookRepository() {
        books = new HashMap<>();

    }

    @Override
    public void create(Book book) {
        ISBN isbn = book.isbn();
        books.put(isbn, book);
    }

    @Override
    public List<Book> searchBooks(Title title) {
        List<Book> booksWithTitle = new MyList<>();
        for (Book currentBook : books.values()) {
            if (currentBook.title().equals(title)) {
                booksWithTitle.add(currentBook);
            }
        }
        return booksWithTitle;
    }

    @Override
    public Book searchBooks(ISBN isbn) {
        return books.get(isbn);
    }

    @Override
    public List<Book> searchByAuthor(String surname) {
        List<Book> booksByAuthor = new MyList<>();
        for (Book currentBook : books.values()) {
            if (currentBook.author().getSurname().equals(surname)) {
                booksByAuthor.add(currentBook);
            }
        }
        return booksByAuthor;
    }

    @Override
    public List<Book> searchByFirstName(String firstName) {
        List<Book> booksByAuthor = new MyList<>();
        for (Book currentBook : books.values()) {
            if (currentBook.author().getFirstName().equals(firstName)) {
                booksByAuthor.add(currentBook);
            }
        }
        return booksByAuthor;
    }

    @Override
    public void delete(ISBN isbn) {
        books.remove(isbn);
    }

    @Override
    public void delete() {

    }
}
