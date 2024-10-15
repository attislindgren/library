package se.thinkcode.library;

import se.thinkcode.infrastructure.DatabaseConnection;

public class SqlLoanRepositoryIT extends LoanRepositoryTest {
    public SqlLoanRepositoryIT() {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        loanRepository = new SqlLoanRepository(databaseConnection);
        bookRepository = new SqlBookRepository(databaseConnection);
        borrowerRepository = new SqlBorrowerRepository(databaseConnection);
        loanRepository.delete();
        bookRepository.delete();
        borrowerRepository.delete();
    }
}
