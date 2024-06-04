package se.thinkcode.version;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface VersionDao {

    @SqlUpdate("""
            INSERT INTO version (version)
            VALUES (:version)
            """)
    void addVersion(@Bind("version") String versionStr);

    @SqlQuery("""
            select version
            from version
            order by version
            limit 1
            """)
    @RegisterRowMapper(VersionMapper.class)
    Version getVersion();
}