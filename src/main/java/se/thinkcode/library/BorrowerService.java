package se.thinkcode.library;

public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Borrower searchBorrower(FirstName name) {
        return borrowerRepository.searchBorrower(name);
    }

    void createBorrower(Borrower borrower) {
        borrowerRepository.createBorrower(borrower);
    }
}