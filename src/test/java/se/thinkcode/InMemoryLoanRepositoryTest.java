package se.thinkcode;

public class InMemoryLoanRepositoryTest extends LoanRepositoryTest {
    public InMemoryLoanRepositoryTest() {
        loanRepository = new InMemoryLoanRepository();
        bookRepository = new InMemoryBookRepository();
    }
}
