package se.thinkcode.library;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BorrowerRepositoryTest {
    BorrowerRepository repository;
    @Test
    void should_create_a_borrower() {
        FirstName firstName = new FirstName("Astrid");
        Borrower borrower = new Borrower(firstName);
        repository.createBorrower(borrower);

        Borrower actual = repository.searchBorrower(firstName);

        assertThat(actual).isEqualTo(borrower);
    }
}
