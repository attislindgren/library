package se.thinkcode.library.v1;

import se.thinkcode.library.Borrower;
import se.thinkcode.library.Email;
import se.thinkcode.library.FirstName;
import se.thinkcode.library.LastName;

public record CreateBorrowerRequest(String firstName, String lastName, String email) {
    public Borrower toModel() {
        FirstName firstName = new FirstName(firstName());
        LastName lastName = new LastName(lastName());
        Email email = new Email(email());
        return new Borrower(firstName, lastName, email);
    }
}
