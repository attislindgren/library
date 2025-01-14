package se.thinkcode.util;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

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

    @Test
    void should_add_two_with_same_position() {
        Map<String, String> actual = new MyMap<>();
        String key = "Vindruva";
        String value = "Röd";
        actual.put(key, value);
        key = "Olive";
        value = "Grön";
        actual.put(key, value);

        assertThat(actual.containsKey("Vindruva")).isTrue();
        assertThat(actual.containsKey("Olive")).isTrue();
    }

    @Test
    void should_add_12_items() {
        Map<String, String> actual = new MyMap<>();
        actual.put("Vindruva", "Röd");
        actual.put("Olive", "Grön");
        actual.put("Paprika", "Gul");
        actual.put("Brie", "Ost");
        actual.put("Sallad", "Isberg");
        actual.put("Äpple", "Rosa");
        actual.put("Te", "Fruktigt");
        actual.put("Kaffe", "Svart");
        actual.put("Appelsin", "Orange");
        actual.put("Vatten", "Blå");
        actual.put("Salt", "Vit");
        actual.put("Ägg", "Vitgul");

        assertThat(actual.size()).isEqualTo(12);
        assertThat(actual.containsKey("Ägg")).isTrue();
    }

    @Test
    void should_add_17_items() {
        Map<Integer, Integer> actual = new MyMap<>();
        for (int i = 0; i < 17; i++) {
            actual.put(i, i);
        }
        assertThat(actual.size()).isEqualTo(17);
    }

    @Test
    void should_remove_item_twelve() {
        Map<Integer, Integer> actual = new MyMap<>();
        for (int i = 0; i < 17; i++) {
            actual.put(i, i);
        }
        actual.remove(2);

        assertThat(actual.size()).isEqualTo(16);
        assertThat(actual).doesNotContainKeys(2);
    }

    @Test
    void should_get_set_of_all_keys() {
        Map<String, String> actual = new MyMap<>();
        actual.put("Vindruva", "Röd");
        actual.put("Olive", "Grön");
        actual.put("Paprika", "Gul");
        actual.put("Brie", "Ost");
        actual.put("Sallad", "Isberg");
        actual.put("Äpple", "Rosa");
        actual.put("Te", "Fruktigt");
        actual.put("Kaffe", "Svart");
        actual.put("Appelsin", "Orange");
        actual.put("Vatten", "Blå");
        actual.put("Salt", "Vit");
        actual.put("Ägg", "Vitgul");

        Set<String> actualSet = actual.keySet();

        assertThat(actualSet.contains("Ägg")).isTrue();
        assertThat(actualSet).contains("Ägg");
        assertThat(actualSet.size()).isEqualTo(12);
    }
}
