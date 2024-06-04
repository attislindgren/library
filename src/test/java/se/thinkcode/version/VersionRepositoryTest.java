package se.thinkcode.version;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class VersionRepositoryTest {

    VersionRepository repository;

    @Test
    void should_get_version() {
        Version expected = new Version("1");
        repository.addVersion(expected);

        Version actual = repository.getVersion();

        assertThat(actual).isEqualTo(expected);
    }
}