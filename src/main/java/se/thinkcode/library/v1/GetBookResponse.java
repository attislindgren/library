package se.thinkcode.library.v1;

import se.thinkcode.library.Book;

public record GetBookResponse(String title, String isbn, String firstname, String surname) {
    public static GetBookResponse fromModel(Book book) {
        String title = book.title().getTitle();
        String isbn = book.isbn().getIsbn();
        String firstName = book.author().firstName();
        String surname = book.author().surname();
        return new GetBookResponse(title, isbn, firstName, surname);
    }
}
