package se.thinkcode.other;

public class Recursion {

    public void callMyself(int i) {
        if (i < 0) {
            return;
        }
        System.out.println(i);
        i--;
        callMyself(i);
    }

    public int sum(int i) {
        if (i == 0) {
            return i;
        } else {
            return i + sum(i - 1);
        }
    }

    public int factorial(int i) {
        if (i <= 1) {
            return 1;
        } else {
            return i * factorial(i - 1);
        }
    }

    public int fib(int i) {
        if (i == 1 || i == 2) {
            return 1;
        } else {
            return fib(i - 1) + fib(i - 2);
        }
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 1 || word.length() == 2) {
            return word.charAt(0) == word.charAt(word.length() - 1);

        } else {
            if (word.charAt(0) == word.charAt(word.length() - 1)) {
                return isPalindrome(word.substring(1, word.length() - 1));
            } else {
                return false;
            }
        }
    }
}
