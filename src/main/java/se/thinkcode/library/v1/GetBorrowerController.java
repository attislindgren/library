package se.thinkcode.library.v1;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import se.thinkcode.library.Borrower;
import se.thinkcode.library.BorrowerService;
import se.thinkcode.library.FirstName;

public class GetBorrowerController implements Handler {
    private final BorrowerService service;

    public GetBorrowerController(BorrowerService service) {
        this.service = service;
    }

    @Override
    public void handle(Context context) {
        String contextName = context.pathParam("name");
        FirstName firstName = new FirstName(contextName);
        Borrower borrower = service.searchBorrower(firstName);
        if (borrower != null) {
            GetBorrowerResponse response = GetBorrowerResponse.fromModel(borrower);
            context.json(response, GetBorrowerResponse.class);
        } else {
            context.status(HttpStatus.NOT_FOUND);
        }
    }
}
