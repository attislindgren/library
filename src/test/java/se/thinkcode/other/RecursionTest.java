package se.thinkcode.other;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RecursionTest {

    @Test
    void print() {
        Recursion recursion = new Recursion();
        recursion.callMyself(9);

    }

    @Test
    void sum_all_numbers() {
        Recursion recursion = new Recursion();
        int actual = recursion.sum(9);

        assertThat(actual).isEqualTo(45);
    }

    @Test
    void should_calculate_factorial() {
        Recursion recursion = new Recursion();
        int actual = recursion.factorial(5);

        assertThat(actual).isEqualTo(120);
    }

    @Test
    void should_print_fib() {
        Recursion recursion = new Recursion();
        int actual = recursion.fib(4);

        assertThat(actual).isGreaterThan(0);
    }

    @Test
    void should_check_for_palindrome() {
        Recursion recursion = new Recursion();
        boolean actual = recursion.isPalindrome("apa");

        assertThat(actual).isTrue();
    }

    @Test
    void bapa_is_not_a_palindrome() {
        Recursion recursion = new Recursion();
        boolean actual = recursion.isPalindrome("bapa");

        assertThat(actual).isFalse();
    }

    @Test
    void aha_is_a_palindrome() {
        Recursion recursion = new Recursion();
        boolean actual = recursion.isPalindrome("aha");

        assertThat(actual).isTrue();
    }
}
