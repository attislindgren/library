package se.thinkcode.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyListTest {

    @Test
    void empty_list_has_size_zero() {
        List<String> actual = new MyList<>();

        assertThat(actual).hasSize(0);
    }

    @Test
    void list_with_one_element_has_size_one() {
        List<String> actual = new MyList<>();
        actual.add("Hej");

        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    void list_with_two_elements_has_size_two() {
        List<String> actual = new MyList<>();
        actual.add("Hej");
        actual.add("då");

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void list_with_three_elements_has_size_three() {
        List<String> actual = new MyList<>();
        actual.add("Hej");
        actual.add("då");
        actual.add("igen");

        assertThat(actual.size()).isEqualTo(3);
    }

}