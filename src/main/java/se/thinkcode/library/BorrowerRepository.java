package se.thinkcode.library;

public interface BorrowerRepository {
    void createBorrower(Borrower borrower);

    Borrower searchBorrower(FirstName firstName);
}
