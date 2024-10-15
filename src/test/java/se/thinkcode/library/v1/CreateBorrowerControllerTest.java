package se.thinkcode.library.v1;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.thinkcode.library.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateBorrowerControllerTest {
    private final Javalin app = Javalin.create();
    private final BorrowerRepository repository = new InMemoryBorrowerRepository();
    BorrowerService service = new BorrowerService(repository);
    CreateBorrowerController controller = new CreateBorrowerController(service);
    JavalinJackson javalinJackson = new JavalinJackson();

    @BeforeEach
    void setup() {
        Routes routes = new Routes();
        routes.overrideController("CreateBorrower", controller);
        routes.routes(app);
    }

    @Test
    void should_create_borrower() {
        CreateBorrowerRequest request = new CreateBorrowerRequest("Axel", "Olofsson", "axel@olofsson.se");
        String json = javalinJackson.toJsonString(request, CreateBorrowerRequest.class);

        JavalinTest.test(app, (server, client) -> {
            try (Response response = client.post("/v1/createBorrower", json)) {
                Borrower actual = service.searchBorrower(new FirstName("Axel"));

                Borrower expected = new Borrower(new FirstName("Axel"), new LastName("Olofsson"), new Email("axel@olofsson.se"));
                assertThat(actual).isEqualTo(expected);
                assertThat(response.code()).isEqualTo(201);
            }
        });
    }
}

