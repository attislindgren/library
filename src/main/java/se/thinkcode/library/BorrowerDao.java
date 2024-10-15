package se.thinkcode.library;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface BorrowerDao {
    @SqlUpdate("""
            INSERT INTO borrower (firstName, lastName, email)
            VALUES (:firstName,:lastName,:email)
            """)
    void createBorrower(@Bind("firstName") String firstName,
                        @Bind("lastName") String lastName,
                        @Bind("email") String email);

    @SqlQuery("""
            select firstName, lastName, email
            from borrower
            where email = :email
            """)
    @RegisterRowMapper(BorrowerMapper.class)
    Borrower searchBorrower(@Bind("email") String email);

    @SqlUpdate("""
            delete
            from borrower
            """)
    void deleteAllBorrowers();
}
