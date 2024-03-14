package se.thinkcode;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    public Library() {
        books = new ArrayList<>();
        ISBN isbn = new ISBN("9780596809485");
        Author author = new Author("Kent", "Beck");
        Book book = new Book("Extreme", isbn, author);
        books.add(book);
        author = new Author("George", "Orwell");
        isbn = new ISBN("9780470059029");
        book = new Book("1984", isbn, author);
        books.add(book);
    }

    public List<Book> searchBooks(String title) {
        List<Book> booksWithTitle = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.title().contains(title)) {
                booksWithTitle.add(currentBook);
            }
        }
        return booksWithTitle;
    }

    public List<Book> searchBooks(ISBN isbn) {
        List<Book> booksWithIsbn = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.isbn().equals(isbn)) {
                booksWithIsbn.add(currentBook);
            }
        }
        return booksWithIsbn;
    }

    public List<Book> searchByAuthor(String surname) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.author().getSurname().equals(surname)) {
                booksByAuthor.add(currentBook);
            }
        }
        return booksByAuthor;
    }

    public List<Book> searchByFirstName(String firstName) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.author().getFirstName().equals(firstName)) {
                booksByAuthor.add(currentBook);
            }
        }
        return booksByAuthor;
    }
}
