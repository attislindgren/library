package se.thinkcode.library.v1;

import se.thinkcode.library.Borrower;
import se.thinkcode.library.FirstName;

public record CreateBorrowerRequest(String name) {
    public Borrower toModel() {
        FirstName firstName = new FirstName(name());
        return new Borrower(firstName);
    }
}
