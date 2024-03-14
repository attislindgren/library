package se.thinkcode;

import java.util.Objects;

public class Book {
    private final String title;
    private final ISBN isbn;
    private final Author author;


    public Book(String title, ISBN isbn, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    public String title() {
        return title;
    }

    public ISBN isbn() {
        return isbn;
    }

    public Author author() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, isbn);
    }
}
