package se.thinkcode.library.v1;

import se.thinkcode.library.Borrower;

public record GetBorrowerResponse(String firstname) {
    public static GetBorrowerResponse fromModel(Borrower borrower) {
        String name = borrower.getName().firstName();
        return new GetBorrowerResponse(name);
    }
}
