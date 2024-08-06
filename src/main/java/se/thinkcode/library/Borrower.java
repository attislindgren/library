package se.thinkcode.library;

import java.util.Objects;

public class Borrower {
    private final FirstName firstName;

    public Borrower(FirstName firstName) {
        this.firstName = firstName;
    }

    public FirstName getName() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return Objects.equals(firstName, borrower.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName);
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "firstName=" + firstName +
                '}';
    }
}
