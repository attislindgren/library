package se.thinkcode.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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

    @Test
    void list_with_four_elements_has_size_four() {
        List<String> actual = new MyList<>();
        actual.add("Hej");
        actual.add("då");
        actual.add("igen");
        actual.add("Per");

        assertThat(actual.size()).isEqualTo(4);
    }

    @Test
    void should_remove_first_item_by_value() {
        List<String> actual = new MyList<>();
        actual.add("Hej");
        actual.add("då");
        actual.add("igen");

        actual.remove("Hej");

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void should_remove_second_item() {
        List<String> actual = new MyList<>();
        actual.add("Hej");
        actual.add("då");
        actual.add("igen");

        actual.remove("då");

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void should_contain_item() {
        List<String> actual = new MyList<>();
        actual.add("Hej");
        actual.add("då");

        assertThat(actual).contains("Hej");
        assertThat(actual).contains("då");
        for (String s : actual) {
            System.out.println(s);
        }
    }

    @Test
    void should_turn_list_to_array() {
        List<String> actual = new MyList<>();
        actual.add("Hej");
        actual.add("då");
        actual.add("igen");

        Object[] actualArray = actual.toArray();

        assertThat(actualArray.length).isEqualTo(3);
        assertThat(actualArray[0]).isEqualTo("Hej");
    }

    @Test
    void should_get_first_and_last_element() {
        List<String> list = new MyList<>();
        list.add("Hej");
        list.add("då");
        list.add("igen");

        String actual = list.get(0);
        assertThat(actual).isEqualTo("Hej");
    }

    @Test
    void should_throw_IndexOutOfBoundsException_when_index_is_too_large() {
        List<String> list = new MyList<>();
        list.add("Hej");
        list.add("då");
        list.add("igen");

        Throwable thrown = catchThrowable(() -> list.get(13));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index 13 out of bounds for length 3");
    }
}