package se.thinkcode.library;

import java.util.Objects;

public class Borrower {
    private final FirstName firstName;
    private final LastName lastName;
    private final Email email;

    public Borrower(FirstName firstName, LastName lastName, Email email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public FirstName getName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public Email getEmail() {
        return email;
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
