package se.thinkcode;

public class InMemoryBookRepositoryTest extends BookRepositoryTest {
    public InMemoryBookRepositoryTest() {
        repository = new InMemoryBookRepository();
    }
}
