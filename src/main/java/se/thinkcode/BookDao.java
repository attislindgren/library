package se.thinkcode;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface BookDao {
    @SqlUpdate("""
            INSERT INTO book (title, isbn, firstName, surname)
            VALUES (:title, :isbn, :firstName, :surname)
            """)
    void createBook(@Bind("title") String title,
                    @Bind("isbn") String isbn,
                    @Bind("firstName") String firstName,
                    @Bind("surname") String surname);

    @SqlQuery("""
            select title, isbn, firstName, surname
            from book
            where isbn = :isbn
            """)
    @RegisterRowMapper(BookMapper.class)
    Book searchBook(@Bind("isbn") String isbn);

    @SqlUpdate("""
            delete
            from book
            where isbn = :isbn
            """)
    void deleteBook(@Bind("isbn") String isbn);

}
