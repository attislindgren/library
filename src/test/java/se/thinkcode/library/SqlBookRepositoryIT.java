package se.thinkcode.library;

import se.thinkcode.infrastructure.DatabaseConnection;

public class SqlBookRepositoryIT extends BookRepositoryTest {
    public SqlBookRepositoryIT() {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        repository = new SqlBookRepository(databaseConnection);
        repository.delete();
    }
}
