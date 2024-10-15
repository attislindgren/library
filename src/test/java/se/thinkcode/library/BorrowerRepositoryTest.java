package se.thinkcode.library;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BorrowerRepositoryTest {
    BorrowerRepository repository;
    @Test
    void should_create_a_borrower() {
        FirstName firstName = new FirstName("Astrid");
        LastName lastName = new LastName("Lindgren");
        Email email = new Email("förnam@efternamn.se");
        Borrower borrower = new Borrower(firstName, lastName, email);

        repository.createBorrower(email, borrower);
        Borrower actual = repository.searchBorrower(email);

        assertThat(actual).isEqualTo(borrower);
    }

}
