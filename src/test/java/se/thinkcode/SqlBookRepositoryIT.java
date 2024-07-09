package se.thinkcode;

import se.thinkcode.infrastructure.DatabaseConnection;

public class SqlBookRepositoryIT extends BookRepositoryTest {
    public SqlBookRepositoryIT() {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        repository = new SqlBookRepository(databaseConnection);
        repository.delete();
    }
}
