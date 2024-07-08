package se.thinkcode;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book map(ResultSet rs, StatementContext ctx) throws SQLException {
        String titlestr = rs.getString("title");
        Title title = new Title(titlestr);
        String isbnstr = rs.getString("isbn");
        ISBN isbn = new ISBN(isbnstr);
        String firstNamestr = rs.getString("firstName");
        String surnamestr = rs.getString("surname");
        Author author = new Author(firstNamestr, surnamestr);
        return new Book(title, isbn, author);
    }
}
