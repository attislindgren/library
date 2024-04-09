package se.thinkcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BorrowerRepositoryTest {

    @Test
    void should_create_a_borrower() {
        BorrowerRepository repository = new BorrowerRepository();
        FirstName firstName = new FirstName("Astrid");
        Borrower borrower = new Borrower(firstName);
        repository.createBorrower(borrower);

        Borrower actual = repository.searchBorrower(firstName);

        assertThat(actual).isEqualTo(borrower);
    }
}
