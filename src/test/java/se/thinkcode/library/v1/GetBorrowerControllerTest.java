package se.thinkcode.library.v1;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.thinkcode.library.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GetBorrowerControllerTest {
    private final Javalin app = Javalin.create();
    private final BorrowerRepository repository = new InMemoryBorrowerRepository();
    BorrowerService service = new BorrowerService(repository);
    GetBorrowerController controller = new GetBorrowerController(service);
    JavalinJackson javalinJackson = new JavalinJackson();

    @BeforeEach
    void setup() {
        Routes routes = new Routes();
        routes.overrideController("GetBorrower", controller);
        routes.routes(app);
    }

    @Test
    void should_get_borrower() {
        Email email = new Email("axel@olofsson.se");
        Borrower borrower = new Borrower(new FirstName("Axel"), new LastName("Olofsson"), email);
        service.createBorrower(email, borrower);
        GetBorrowerResponse expected = new GetBorrowerResponse("Axel");

        JavalinTest.test(app, (server, client) -> {
            Response response = client.get("/v1/getBorrower/Axel");

            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body()).isNotNull();

            GetBorrowerResponse actual = javalinJackson.fromJsonStream(response.body().byteStream(), GetBorrowerResponse.class);

            assertThat(actual).isEqualTo(expected);
        });
    }
}
