package se.thinkcode.library;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import se.thinkcode.infrastructure.DatabaseConnection;
import se.thinkcode.library.v1.CreateBookController;
import se.thinkcode.library.v1.GetBookController;

import java.util.HashMap;
import java.util.Map;

public class Routes {
    private final Map<String, Handler> controllers = new HashMap<>();

    public Routes() {
    }

    public void routes(Javalin app) {
        app.post("/v1/createBook", getCreateBookController());
        app.get("/v1/getBook/{isbn}", getBookController());
    }

    public void overrideController(String key, Handler controller) {
        controllers.put(key, controller);
    }

    private CreateBookController getCreateBookController() {
        if (controllers.containsKey("CreateBook")) {
            return (CreateBookController) controllers.get("CreateBook");
        }
        BookRepository bookRepository = getBookRepository();

        return new CreateBookController(new LibraryService(bookRepository, null, null, null));
    }

    private GetBookController getBookController() {
        if (controllers.containsKey("GetBook")) {
            return (GetBookController) controllers.get("GetBook");
        }
        BookRepository bookRepository = getBookRepository();

        return new GetBookController(new LibraryService(bookRepository, null, null, null));
    }

    private BookRepository getBookRepository() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return new SqlBookRepository(databaseConnection);
    }

}