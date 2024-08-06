package se.thinkcode.library;

public class InMemoryLoanRepositoryTest extends LoanRepositoryTest {
    public InMemoryLoanRepositoryTest() {
        loanRepository = new InMemoryLoanRepository();
        bookRepository = new InMemoryBookRepository();
    }
}
