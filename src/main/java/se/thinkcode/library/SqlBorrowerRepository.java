package se.thinkcode.library;

import se.thinkcode.infrastructure.DatabaseConnection;

public class SqlBorrowerRepository implements BorrowerRepository {
    private final DatabaseConnection databaseConnection;

    public SqlBorrowerRepository(DatabaseConnection databaseConnection) {

        this.databaseConnection = databaseConnection;
    }

    @Override
    public void createBorrower(Borrower borrower) {
        BorrowerDao dao = databaseConnection.getBorrowerDao();
        String firstName = borrower.getName().firstName();
        dao.createBorrower(firstName);
    }

    @Override
    public Borrower searchBorrower(FirstName firstName) {
        BorrowerDao dao = databaseConnection.getBorrowerDao();
        String name = firstName.firstName();
        return dao.searchBorrower(name);
    }
}
