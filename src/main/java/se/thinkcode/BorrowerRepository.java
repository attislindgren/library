package se.thinkcode;

import java.util.ArrayList;
import java.util.List;

public class BorrowerRepository {
    private final List<Borrower> borrowers;


    public BorrowerRepository() {
        borrowers = new ArrayList<>();
        FirstName firstName = new FirstName("Kent");
        Borrower borrower = new Borrower(firstName);
        createBorrower(borrower);

        firstName = new FirstName("Olle");
        borrower = new Borrower(firstName);
        createBorrower(borrower);
    }

    public void createBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public Borrower searchBorrower(FirstName firstName) {
        for (Borrower currentBorrower : borrowers) {
            if (currentBorrower.getName().equals(firstName)) {
                return currentBorrower;
            }
        }
        return null;

    }
}
