package se.thinkcode;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HikerTest {
    @Test
    void should_verify_unit_test_env() {
        String expected = "Hello!";
        Hiker hiker = new Hiker();

        String actual = hiker.hello();

        assertThat(actual).isEqualTo(expected);
    }
}
