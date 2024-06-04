package se.thinkcode.version;

import se.thinkcode.infrastructure.DatabaseConnection;

public class SqlVersionRepository implements VersionRepository {
    private final DatabaseConnection databaseConnection;

    public SqlVersionRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void addVersion(Version version) {
        VersionDao dao = databaseConnection.getVersionDao();

        String versionStr = version.version();

        dao.addVersion(versionStr);
    }

    @Override
    public Version getVersion() {
        VersionDao dao = databaseConnection.getVersionDao();

        return dao.getVersion();
    }
}