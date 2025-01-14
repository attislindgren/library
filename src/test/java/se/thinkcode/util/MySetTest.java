package se.thinkcode.util;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MySetTest {

    @Test
    void should_add_item_to_set() {
        Set<String> actual = new MySet<>();
        actual.add("First");

        assertThat(actual).contains("First");
    }

    @Test
    void should_contain_item_in_set() {
        Set<String> actual = new MySet<>();
        actual.add("First");
        actual.add("Second");

        assertThat(actual.contains("First")).isTrue();
    }

    @Test
    void should_only_contain_one_item() {
        Set<String> actual = new MySet<>();
        actual.add("First");
        actual.add("First");

        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    void should_remove_item() {
        Set<String> actual = new MySet<>();
        actual.add("First");
        actual.add("Second");

        actual.remove("First");

        assertThat(actual).doesNotContain("First");
    }

    @Test
    void Should_clear_set() {
        Set<String> actual = new MySet<>();
        actual.add("First");
        actual.add("Second");

        actual.clear();

        assertThat(actual).isEmpty();
    }
}
