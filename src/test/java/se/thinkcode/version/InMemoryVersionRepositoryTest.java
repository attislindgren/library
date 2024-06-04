package se.thinkcode.version;

class InMemoryVersionRepositoryTest extends VersionRepositoryTest {

    public InMemoryVersionRepositoryTest() {
        repository = new InMemoryVersionRepository();
    }
}