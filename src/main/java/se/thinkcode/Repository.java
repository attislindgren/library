package se.thinkcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private final List<Book> bookList;
    private final Map<ISBN, Book> books;

    public Repository() {
        books = new HashMap<>();
        bookList = new ArrayList<>();
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Title title = new Title("Extreme");
        Book book = new Book(title, isbn, author);
        bookList.add(book);
        books.put(isbn, book);
        author = new Author("George", "Orwell");
        isbn = new ISBN("9780470059029");
        title = new Title("1984");
        book = new Book(title, isbn, author);
        bookList.add(book);
        books.put(isbn, book);
    }

    public List<Book> searchBooks(Title title) {
        List<Book> booksWithTitle = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            Book currentBook = bookList.get(i);
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
        for (int i = 0; i < bookList.size(); i++) {
            Book currentBook = bookList.get(i);
            if (currentBook.author().getFirstName().equals(firstName)) {
                booksByAuthor.add(currentBook);
            }
        }
        return booksByAuthor;
    }
}
