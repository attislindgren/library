package se.thinkcode.library.v1;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.thinkcode.library.*;

import static org.assertj.core.api.Assertions.assertThat;

class CreateBookControllerTest {
    private final Javalin app = Javalin.create();
    BookRepository repository = new InMemoryBookRepository();
    LibraryService service = new LibraryService(repository, null, null, null);
    CreateBookController createBookController = new CreateBookController(service);
    GetBookController getBookController = new GetBookController(service);
    JavalinJackson javalinJackson = new JavalinJackson();

    @BeforeEach
    void setup() {
        app.post("/v1/createBook", createBookController);
        app.get("/v1/getBook/{isbn}", getBookController);
    }
    @Test
    void should_create_book() {
        CreateBookRequest request = new CreateBookRequest("1984", "9780470059029", "George", "Orwell");
        String json = javalinJackson.toJsonString(request, CreateBookRequest.class);

        JavalinTest.test(app, (server, client) -> {
            try (Response response = client.post("/v1/createBook", json)) {

                Book actual = service.searchBooks(new ISBN("9780470059029"));

                assertThat(actual.title()).isEqualTo(new Title("1984"));
                assertThat(response.code()).isEqualTo(201);
            }
        });
    }

    @Test
    void should_get_book() {
        Book book = new Book(new Title("1984"), new ISBN("9780470059029"), new Author("George", "Orwell"));
        service.createBook(book);
        GetBookResponse expected = new GetBookResponse("1984", "9780470059029", "George", "Orwell");

        JavalinTest.test(app, (server, client) -> {
            Response response = client.get("/v1/getBook/9780470059029");

            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body()).isNotNull();

            GetBookResponse actual = javalinJackson.fromJsonStream(response.body().byteStream(), GetBookResponse.class);
            assertThat(actual).isEqualTo(expected);
        });
    }

    @Test
    void should_create_and_get_book() {
        CreateBookRequest request = new CreateBookRequest("Jurassic Park", "9789171195739", "Michael", "Crichton");
        String json = javalinJackson.toJsonString(request, CreateBookRequest.class);
        GetBookResponse expected = new GetBookResponse("Jurassic Park", "9789171195739", "Michael", "Crichton");

        JavalinTest.test(app, (server, client) -> {
            Response createResponse = client.post("/v1/createBook", json);

            Book actual = service.searchBooks(new ISBN("9789171195739"));

            assertThat(actual.title()).isEqualTo(new Title("Jurassic Park"));
            assertThat(createResponse.code()).isEqualTo(201);

            Response getResponse = client.get("/v1/getBook/9789171195739");

            assertThat(getResponse.code()).isEqualTo(200);
            assertThat(getResponse.body()).isNotNull();

            GetBookResponse actual1 = javalinJackson.fromJsonStream(getResponse.body().byteStream(), GetBookResponse.class);
            assertThat(actual1).isEqualTo(expected);
        });
    }
}