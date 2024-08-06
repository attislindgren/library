package se.thinkcode.library;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LoanMapper implements RowMapper<Loan> {
    @Override
    public Loan map(ResultSet rs, StatementContext ctx) throws SQLException {
        LocalDate loanDate = rs.getDate("loanDate").toLocalDate();
        String titlestr = rs.getString("title");
        Title title = new Title(titlestr);
        String isbnstr = rs.getString("isbn");
        ISBN isbn = new ISBN(isbnstr);
        String firstNamestr = rs.getString("firstName");
        String surnamestr = rs.getString("surname");
        Author author = new Author(firstNamestr, surnamestr);
        Book book = new Book(title, isbn, author);
        return new Loan(book, loanDate);
    }
}
