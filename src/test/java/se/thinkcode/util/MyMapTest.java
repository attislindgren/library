package se.thinkcode.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MyMapTest {

    @Test
    void should_shrink_hashcode_to_fit_in_bucket() {
        String key = "Hej";
        System.out.println(key.hashCode());

        // Modulo 5 gives a number between 0-4.
        // This can be used to determine which bucket in an array that holds the value
        int actual = key.hashCode() % 5;

        assertThat(actual).isBetween(0, 4);
        System.out.println(actual);
    }

    @Test
    void should_add_item_to_map() {
        Map<String, String> actual = new MyMap<>();
        String key = "Ost";
        String value = "Brie";

        actual.put(key, value);

        assertThat(actual.get("Ost")).isEqualTo("Brie");
    }

    @Test
    void should_verify_list_is_empty() {
        Map<String, String> actual = new MyMap<>();

        assertThat(actual.isEmpty()).isTrue();
    }

    @Test
    void map_with_two_items_should_have_size_two() {
        Map<String, String> actual = new MyMap<>();
        String key = "Ost";
        String value = "Brie";
        actual.put(key, value);
        key = "Vindruva";
        value = "Röd";
        actual.put(key, value);

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void map_with_three_items_should_have_size_three() {
        Map<String, String> actual = new MyMap<>();
        String key = "Ost";
        String value = "Brie";
        actual.put(key, value);
        key = "Vindruva";
        value = "Röd";
        actual.put(key, value);
        key = "Paprika";
        value = "Grön";
        actual.put(key, value);

        assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    void should_remove_item_from_map() {
        Map<String, String> actual = new MyMap<>();
        String key = "Ost";
        String value = "Brie";
        actual.put(key, value);
        key = "Vindruva";
        value = "Röd";
        actual.put(key, value);
        key = "Paprika";
        value = "Grön";
        actual.put(key, value);

        actual.remove("Paprika");

        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual).doesNotContainKeys("Paprika");
    }

    @Test
    void should_clear_map() {
        Map<String, String> actual = new MyMap<>();
        String key = "Ost";
        String value = "Brie";
        actual.put(key, value);
        key = "Vindruva";
        value = "Röd";
        actual.put(key, value);

        actual.clear();

        assertThat(actual.isEmpty()).isTrue();
        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    void should_contain_value() {
        Map<String, String> actual = new MyMap<>();
        String key = "Ost";
        String value = "Brie";
        actual.put(key, value);
        key = "Vindruva";
        value = "Röd";
        actual.put(key, value);

        assertThat(actual.containsValue("Röd")).isTrue();
    }
}
