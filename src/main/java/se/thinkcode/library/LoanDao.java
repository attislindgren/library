package se.thinkcode.library;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.util.List;

public interface LoanDao {
    @SqlUpdate("""
            INSERT INTO loan (isbn, email, loanDate, returnDate)
            VALUES (:isbn, :email, :loanDate,null)
            """)
    void borrowBook(@Bind("isbn") String isbn,
                    @Bind("email") String email,
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
            where loan.email = :email
            """)
    @RegisterRowMapper(BookMapper.class)
    List<Book> getBooksBorrowedBy(@Bind("email") String email);

    @SqlUpdate("""
            update loan
            set returnDate = :returnDate
            where isbn = :isbn and
            email = :email
            """)
    void returnBook(@Bind("isbn") String isbn,
                    @Bind("email") String email,
                    @Bind("returnDate") LocalDate returnDate);

    @SqlQuery("""
            select returnDate
            from loan
            where email = :email and
            isbn = :isbn
            """)
    LocalDate getDateOfReturn(@Bind("isbn") String isbn,
                              @Bind("email") String email);

    @SqlQuery("""
            select loanDate
            from loan
            where email = :email and
            isbn = :isbn
            """)
    LocalDate getDateOfLoan(@Bind("isbn") String isbn,
                            @Bind("email") String email);

    @SqlQuery("""
            select title, book.isbn, book.firstName, surname, loanDate
            from book
            join loan on loan.isbn=book.isbn
            where loan.email = :email and
            loan.isbn = :isbn
            """)
    @RegisterRowMapper(LoanMapper.class)
    Loan getLoan(@Bind("isbn") String isbn,
                 @Bind("email") String email);

    @SqlQuery("""
            select title, book.isbn, book.firstName, surname, loanDate
            from book
            join loan on loan.isbn=book.isbn
            where loan.email = :email
            """)
    @RegisterRowMapper(LoanMapper.class)
    List<Loan> getLoans(@Bind("email") String email);

    @SqlQuery("""
            select firstName, lastName, loan.email
            from loan
            join borrower on borrower.email=loan.email
            """)
    @RegisterRowMapper(BorrowerMapper.class)
    List<Borrower> getBorrowers();
}
