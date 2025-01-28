package se.thinkcode.library;

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
        String firstName = book.author().firstName();
        String surname = book.author().surname();
        dao.createBook(title, isbn, firstName, surname);
    }

    @Override
    public List<Book> searchBooks(Title title) {
        BookDao dao = databaseConnection.getBookDao();
        String titleStr = title.getTitle();
        return dao.searchBookByTitle(titleStr);
    }

    @Override
    public Book searchBooks(ISBN isbn) {
        BookDao dao = databaseConnection.getBookDao();
        String isbnstr = isbn.getIsbn();
        return dao.searchBook(isbnstr);
    }

    @Override
    public List<Book> searchByAuthor(String surname) {
        BookDao dao = databaseConnection.getBookDao();
        return dao.searchBookByAuthor(surname);
    }

    @Override
    public List<Book> searchByFirstName(String firstName) {
        BookDao dao = databaseConnection.getBookDao();
        return dao.searchBookByFirstName(firstName);
    }

    @Override
    public void delete(ISBN isbn) {
        BookDao dao = databaseConnection.getBookDao();
        String isbnstr = isbn.getIsbn();
        dao.deleteBook(isbnstr);
    }

    @Override
    public void delete() {
        BookDao dao = databaseConnection.getBookDao();
        dao.deleteBook();
    }
}
