package se.thinkcode;

class InMemoryBorrowerRepositoryTest extends BorrowerRepositoryTest {
    public InMemoryBorrowerRepositoryTest() {
        repository = new InMemoryBorrowerRepository();
    }
}
