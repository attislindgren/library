package se.thinkcode.library;

public class InMemoryBookRepositoryTest extends BookRepositoryTest {
    public InMemoryBookRepositoryTest() {
        repository = new InMemoryBookRepository();
    }
}
