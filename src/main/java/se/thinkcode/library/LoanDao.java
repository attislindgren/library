package se.thinkcode.library;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.util.List;

public interface LoanDao {
    @SqlUpdate("""
            INSERT INTO loan (isbn, firstName, loanDate, returnDate)
            VALUES (:isbn, :firstName, :loanDate,null)
            """)
    void borrowBook(@Bind("isbn") String isbn,
                    @Bind("firstName") String firstName,
                    @Bind("loanDate") LocalDate loanDate);

    @SqlUpdate("""
            delete
            from loan
            """)
    void delete();

    @SqlQuery("""
            select title, book.isbn, book.firstName, surname
            from book
            join loan on loan.isbn=book.isbn
            where loan.firstName = :firstName
            """)
    @RegisterRowMapper(BookMapper.class)
    List<Book> getBooksBorrowedBy(@Bind("firstName") String firstName);

    @SqlUpdate("""
            update loan
            set returnDate = :returnDate
            where isbn = :isbn and
            firstName = :firstName
            """)
    void returnBook(@Bind("isbn") String isbn,
                    @Bind("firstName") String firstName,
                    @Bind("returnDate") LocalDate returnDate);

    @SqlQuery("""
            select returnDate
            from loan
            where firstName = :firstName and
            isbn = :isbn
            """)
    LocalDate getDateOfReturn(@Bind("isbn") String isbn,
                              @Bind("firstName") String firstName);

    @SqlQuery("""
            select loanDate
            from loan
            where firstName = :firstName and
            isbn = :isbn
            """)
    LocalDate getDateOfLoan(@Bind("isbn") String isbn,
                            @Bind("firstName") String firstName);

    @SqlQuery("""
            select title, book.isbn, book.firstName, surname, loanDate
            from book
            join loan on loan.isbn=book.isbn
            where loan.firstName = :firstName and
            loan.isbn = :isbn
            """)
    @RegisterRowMapper(LoanMapper.class)
    Loan getLoan(@Bind("isbn") String isbn,
                 @Bind("firstName") String firstName);

    @SqlQuery("""
            select title, book.isbn, book.firstName, surname, loanDate
            from book
            join loan on loan.isbn=book.isbn
            where loan.firstName = :firstName
            """)
    @RegisterRowMapper(LoanMapper.class)
    List<Loan> getLoans(@Bind("firstName") String firstName);

    @SqlQuery("""
            select firstName
            from loan
            """)
    @RegisterRowMapper(BorrowerMapper.class)
    List<Borrower> getBorrowers();
}
