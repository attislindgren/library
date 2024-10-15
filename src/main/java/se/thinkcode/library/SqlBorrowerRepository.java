package se.thinkcode.library;

import se.thinkcode.infrastructure.DatabaseConnection;

public class SqlBorrowerRepository implements BorrowerRepository {
    private final DatabaseConnection databaseConnection;

    public SqlBorrowerRepository(DatabaseConnection databaseConnection) {

        this.databaseConnection = databaseConnection;
    }
    @Override
    public void createBorrower(Email email, Borrower borrower) {
        BorrowerDao dao = databaseConnection.getBorrowerDao();
        String firstName = borrower.getName().firstName();
        String lastName = borrower.getLastName().lastName();
        String epost = borrower.getEmail().email();
        dao.createBorrower(firstName, lastName, epost);
    }


    @Override
    public Borrower searchBorrower(FirstName firstName) {
        BorrowerDao dao = databaseConnection.getBorrowerDao();
        String name = firstName.firstName();
        return dao.searchBorrower(name);
    }

    @Override
    public Borrower searchBorrower(Email email) {
        BorrowerDao dao = databaseConnection.getBorrowerDao();
        String epost = email.email();
        return dao.searchBorrower(epost);
    }

    @Override
    public void delete() {
        BorrowerDao dao = databaseConnection.getBorrowerDao();
        dao.deleteAllBorrowers();

    }
}
