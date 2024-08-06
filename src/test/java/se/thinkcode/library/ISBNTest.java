package se.thinkcode.library;


import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ISBNTest {

    @Test
    void should_compare_isbn() {
        ISBN first = new ISBN("9780470059029");
        ISBN second = new ISBN("9780596809485");

        assertNotEquals(first, second);
    }

    @Test
    void should_compare_equal_isbn() {
        ISBN first = new ISBN("9780470059029");
        ISBN second = new ISBN("9780470059029");

        assertEquals(first, second);
    }

    @Test
    void should_not_accept_incorrect_isbn() {
        try {
            new ISBN("17");
            fail("Should have thrown an exception");
        } catch (Exception e) {
            // empty, everything is good
        }
    }
}
