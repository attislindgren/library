package se.thinkcode.library;

class InMemoryBorrowerRepositoryTest extends BorrowerRepositoryTest {
    public InMemoryBorrowerRepositoryTest() {
        repository = new InMemoryBorrowerRepository();
    }
}
