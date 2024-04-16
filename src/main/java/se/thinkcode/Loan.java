package se.thinkcode;

import java.util.Date;
import java.util.Objects;

public class Loan {
    private final Book book;
    private final Date time;

    public Loan(Book book, Date time) {
        this.book = book;
        this.time = time;
    }

    public Book getBook() {
        return book;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + book +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(book, loan.book) && Objects.equals(time, loan.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, time);
    }
}
