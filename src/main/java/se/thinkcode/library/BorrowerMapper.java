package se.thinkcode.library;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowerMapper implements RowMapper<Borrower> {
    @Override
    public Borrower map(ResultSet rs, StatementContext ctx) throws SQLException {
        String name = rs.getString("firstName");
        FirstName firstName = new FirstName(name);
        String lastNameStr = rs.getString("lastName");
        LastName lastName = new LastName(lastNameStr);
        String emailStr = rs.getString("email");
        Email email = new Email(emailStr);
        return new Borrower(firstName, lastName, email);
    }
}
