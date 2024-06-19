package se.thinkcode;

public interface BorrowerRepository {
    void createBorrower(Borrower borrower);

    Borrower searchBorrower(FirstName firstName);
}
