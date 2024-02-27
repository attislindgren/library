package se.thinkcode;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    public Library() {
        books = new ArrayList<>();
        Book book = new Book("Extreme");
        books.add(book);
        book = new Book("1984");
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
}
