package se.thinkcode.library;

public interface BorrowerRepository {
    void createBorrower(Email email, Borrower borrower);

    Borrower searchBorrower(FirstName firstName);

    Borrower searchBorrower(Email email);

    void delete();
}
