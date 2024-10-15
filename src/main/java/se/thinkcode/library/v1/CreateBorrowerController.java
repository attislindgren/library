package se.thinkcode.library.v1;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import se.thinkcode.library.Borrower;
import se.thinkcode.library.BorrowerService;
import se.thinkcode.library.Email;

public class CreateBorrowerController implements Handler {

    private final BorrowerService service;

    public CreateBorrowerController(BorrowerService service) {
        this.service = service;
    }

    @Override
    public void handle(Context context) {
        CreateBorrowerRequest request = context.bodyAsClass(CreateBorrowerRequest.class);
        Borrower borrower = request.toModel();
        Email email = borrower.getEmail();
        service.createBorrower(email, borrower);
        context.status(HttpStatus.CREATED);
    }
}
