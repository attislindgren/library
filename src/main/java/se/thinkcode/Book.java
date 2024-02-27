package se.thinkcode;

import java.util.Objects;

public class Book {
    private final String title;

    public Book(String title) {
        this.title = title;
    }
    public String title() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
