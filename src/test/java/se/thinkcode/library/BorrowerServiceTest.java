package se.thinkcode.library;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BorrowerServiceTest {
    private final BorrowerRepository borrowerRepository = new InMemoryBorrowerRepository();
    private final BorrowerService borrowerService = new BorrowerService(borrowerRepository);

    @Test
    void should_create_and_find_borrower_by_first_name() {
        FirstName name = new FirstName("Sten");
        LastName lastName = new LastName("Stensson");
        Email email = new Email("sten@stensson.se");
        Borrower expected = new Borrower(name, lastName, email);
        borrowerService.createBorrower(email, expected);

        Borrower actual = borrowerService.searchBorrower(name);

        assertThat(actual).isEqualTo(expected);
    }


}
