package se.thinkcode.library;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import se.thinkcode.infrastructure.DatabaseConnection;
import se.thinkcode.library.v1.CreateBookController;
import se.thinkcode.library.v1.CreateBorrowerController;
import se.thinkcode.library.v1.GetBookController;
import se.thinkcode.library.v1.GetBorrowerController;

import java.util.HashMap;
import java.util.Map;

public class Routes {
    private final Map<String, Handler> controllers = new HashMap<>();

    public Routes() {
    }

    public void routes(Javalin app) {
        app.post("/v1/createBook", getCreateBookController());
        app.get("/v1/getBook/{isbn}", getBookController());
        app.post("/v1/createBorrower", getCreateBorrowerController());
        app.get("/v1/getBorrower/{name}", getBorrowerController());
    }


    public void overrideController(String key, Handler controller) {
        controllers.put(key, controller);
    }

    private CreateBorrowerController getCreateBorrowerController() {
        if (controllers.containsKey("CreateBorrower")) {
            return (CreateBorrowerController) controllers.get("CreateBorrower");
        }
        BorrowerRepository borrowerRepository = getBorrowerRepository();
        BorrowerService borrowerService = new BorrowerService(borrowerRepository);

        return new CreateBorrowerController(borrowerService);

    }

    private CreateBookController getCreateBookController() {
        if (controllers.containsKey("CreateBook")) {
            return (CreateBookController) controllers.get("CreateBook");
        }
        BookRepository bookRepository = getBookRepository();
        BookService bookService = new BookService(bookRepository);

        return new CreateBookController(bookService);
    }

    private GetBookController getBookController() {
        if (controllers.containsKey("GetBook")) {
            return (GetBookController) controllers.get("GetBook");
        }
        BookRepository bookRepository = getBookRepository();
        BookService bookService = new BookService(bookRepository);

        return new GetBookController(bookService);
    }

    private GetBorrowerController getBorrowerController() {
        if (controllers.containsKey("GetBorrower")) {
            return (GetBorrowerController) controllers.get("GetBorrower");
        }
        BorrowerRepository borrowerRepository = getBorrowerRepository();
        BorrowerService borrowerService = new BorrowerService(borrowerRepository);

        return new GetBorrowerController(borrowerService);
    }

    private BookRepository getBookRepository() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return new SqlBookRepository(databaseConnection);
    }

    private BorrowerRepository getBorrowerRepository() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return new SqlBorrowerRepository(databaseConnection);
    }
}