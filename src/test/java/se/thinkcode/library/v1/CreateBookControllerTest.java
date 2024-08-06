package se.thinkcode.library.v1;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import se.thinkcode.library.*;

import static org.assertj.core.api.Assertions.assertThat;

class CreateBookControllerTest {
    private final Javalin app = Javalin.create();

    @Test
    void should_create_book() {
        BookRepository repository = new InMemoryBookRepository();
        LibraryService service = new LibraryService(repository, null, null, null);
        CreateBookController controller = new CreateBookController(service);
        app.post("/v1/createBook", controller);

        CreateBookRequest request = new CreateBookRequest("1984", "9780470059029", "George", "Orwell");

        JavalinJackson javalinJackson = new JavalinJackson();
        String json = javalinJackson.toJsonString(request, CreateBookRequest.class);

        JavalinTest.test(app, (server, client) -> {
            try (Response response = client.post("/v1/createBook", json)) {

                Book actual = service.searchBooks(new ISBN("9780470059029"));

                assertThat(actual.title()).isEqualTo(new Title("1984"));
                assertThat(response.code()).isEqualTo(201);
            }
        });
    }
}