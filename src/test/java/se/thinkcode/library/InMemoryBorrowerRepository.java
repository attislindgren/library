package se.thinkcode.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBorrowerRepository implements BorrowerRepository {
    private final List<Borrower> borrowers;
    private final Map<Email, Borrower> borrowerMap = new HashMap<>();


    public InMemoryBorrowerRepository() {
        borrowers = new ArrayList<>();
        FirstName firstName = new FirstName("Kent");
        LastName lastName = new LastName("Sten");
        Email email = new Email("kent@sten.se");
        Borrower borrower = new Borrower(firstName, lastName, email);
        createBorrower(email, borrower);

        firstName = new FirstName("Olle");
        email = new Email("olle@sten.se");
        borrower = new Borrower(firstName, lastName, email);
        createBorrower(email, borrower);
    }

    @Override
    public void createBorrower(Email email, Borrower borrower) {
        borrowers.add(borrower);
        this.borrowerMap.put(email, borrower);
    }

    @Override
    public Borrower searchBorrower(FirstName firstName) {
        for (Borrower currentBorrower : borrowers) {
            if (currentBorrower.getName().equals(firstName)) {
                return currentBorrower;
            }
        }
        return null;

    }

    @Override
    public Borrower searchBorrower(Email email) {
        return this.borrowerMap.get(email);
    }

    @Override
    public void delete() {

    }
}
