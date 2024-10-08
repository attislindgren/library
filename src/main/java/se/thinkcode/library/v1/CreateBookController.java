package se.thinkcode.library.v1;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import se.thinkcode.library.Book;
import se.thinkcode.library.BookService;

public class CreateBookController implements Handler {
    private final BookService service;

    public CreateBookController(BookService service) {

        this.service = service;
    }

    @Override
    public void handle(Context context) {
        CreateBookRequest request = context.bodyAsClass(CreateBookRequest.class);
        Book book = request.toModel();
        service.createBook(book);
        context.status(HttpStatus.CREATED);
    }
}
