package se.thinkcode.library.v1;

import se.thinkcode.library.Author;
import se.thinkcode.library.Book;
import se.thinkcode.library.ISBN;
import se.thinkcode.library.Title;

public record CreateBookRequest(String title, String isbn, String firstname, String surname) {
    public Book toModel() {
        Title title = new Title(title());
        ISBN isbn = new ISBN(isbn());
        Author author = new Author(firstname(), surname());
        return new Book(title, isbn, author);
    }
}
