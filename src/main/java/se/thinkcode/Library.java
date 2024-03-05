package se.thinkcode;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    public Library() {
        books = new ArrayList<>();
        ISBN isbn = new ISBN("9780596809485");
        Book book = new Book("Extreme", isbn);
        books.add(book);
        isbn = new ISBN("9780470059029");
        book = new Book("1984", isbn);
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
}
