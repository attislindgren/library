package se.thinkcode.library.v1;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import se.thinkcode.library.Book;
import se.thinkcode.library.ISBN;
import se.thinkcode.library.LibraryService;

public class GetBookController implements Handler {
    private final LibraryService service;

    public GetBookController(LibraryService service) {

        this.service = service;
    }

    @Override
    public void handle(Context context) {
        String contextIsbn = context.pathParam("isbn");
        ISBN isbn = new ISBN(contextIsbn);
        Book book = service.searchBooks(isbn);
        GetBookResponse response = GetBookResponse.fromModel(book);
        context.json(response, GetBookResponse.class);
    }
}
