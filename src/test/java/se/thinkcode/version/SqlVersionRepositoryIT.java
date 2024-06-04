package se.thinkcode.version;

import se.thinkcode.infrastructure.DatabaseConnection;

class SqlVersionRepositoryIT extends VersionRepositoryTest {

    public SqlVersionRepositoryIT() {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        repository = new SqlVersionRepository(databaseConnection);
    }
}