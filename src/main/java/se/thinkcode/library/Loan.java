package se.thinkcode.library;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private final Book book;
    private final LocalDate dateBorrowed;
    private LocalDate returnDate;

    public Loan(Book book, LocalDate dateBorrowed) {
        this.book = book;
        this.dateBorrowed = dateBorrowed;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + book +
                ", time=" + dateBorrowed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(book, loan.book) && Objects.equals(dateBorrowed, loan.dateBorrowed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, dateBorrowed);
    }


}
