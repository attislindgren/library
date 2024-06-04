package se.thinkcode.version;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VersionMapper implements RowMapper<Version> {
    @Override
    public Version map(ResultSet rs, StatementContext ctx) throws SQLException {
        String version = rs.getString("version");

        return new Version(version);
    }
}