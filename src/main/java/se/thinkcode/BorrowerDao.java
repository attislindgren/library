package se.thinkcode;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface BorrowerDao {
    @SqlUpdate("""
            INSERT INTO borrower (firstName)
            VALUES (:firstName)
            """)
    void createBorrower(@Bind("firstName") String firstName);

    @SqlQuery("""
            select firstName
            from borrower
            where firstName = :name
            """)
    @RegisterRowMapper(BorrowerMapper.class)
    Borrower searchBorrower(@Bind("name") String name);
}
