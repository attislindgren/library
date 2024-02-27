package se.thinkcode;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public List<Book> searchBooks(String title) {
        List<Book> books = new ArrayList<>();
        List<Book> booksWithTitle = new ArrayList<>();
        Book book = new Book("Extreme");
        books.add(book);
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.title().contains(title)) {
                booksWithTitle.add(currentBook);
            }
        }
        return booksWithTitle;
    }
}
