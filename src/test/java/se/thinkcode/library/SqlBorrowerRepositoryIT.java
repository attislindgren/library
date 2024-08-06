package se.thinkcode.library;

import se.thinkcode.infrastructure.DatabaseConnection;

public class SqlBorrowerRepositoryIT extends BorrowerRepositoryTest {
    public SqlBorrowerRepositoryIT() {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        repository = new SqlBorrowerRepository(databaseConnection);
    }
}
