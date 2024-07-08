package se.thinkcode;

import se.thinkcode.infrastructure.DatabaseConnection;

import java.util.List;

public class SqlBookRepository implements BookRepository {
    private final DatabaseConnection databaseConnection;

    public SqlBookRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void create(Book book) {
        BookDao dao = databaseConnection.getBookDao();
        String title = book.title().getTitle();
        String isbn = book.isbn().getIsbn();
        String firstName = book.author().getFirstName();
        String surname = book.author().getSurname();
        dao.createBook(title, isbn, firstName, surname);
    }

    @Override
    public List<Book> searchBooks(Title title) {
        return null;
    }

    @Override
    public Book searchBooks(ISBN isbn) {
        BookDao dao = databaseConnection.getBookDao();
        String isbnstr = isbn.getIsbn();
        return dao.searchBook(isbnstr);
    }

    @Override
    public List<Book> searchByAuthor(String surname) {
        return null;
    }

    @Override
    public List<Book> searchByFirstName(String firstName) {
        return null;
    }

    @Override
    public void delete(ISBN isbn) {
        BookDao dao = databaseConnection.getBookDao();
        String isbnstr = isbn.getIsbn();
        dao.deleteBook(isbnstr);
    }
}
