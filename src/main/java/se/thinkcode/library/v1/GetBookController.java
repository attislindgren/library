package se.thinkcode.library.v1;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import se.thinkcode.library.Book;
import se.thinkcode.library.BookService;
import se.thinkcode.library.ISBN;

public class GetBookController implements Handler {
    private final BookService service;

    public GetBookController(BookService service) {

        this.service = service;
    }

    @Override
    public void handle(Context context) {
        String contextIsbn = context.pathParam("isbn");
        ISBN isbn = new ISBN(contextIsbn);
        Book book = service.searchBooks(isbn);
        if (book != null) {
            GetBookResponse response = GetBookResponse.fromModel(book);
            context.json(response, GetBookResponse.class);
        } else {
            context.status(HttpStatus.NOT_FOUND);
        }
    }
}
